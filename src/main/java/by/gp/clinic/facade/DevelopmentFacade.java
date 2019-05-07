package by.gp.clinic.facade;

import by.gp.clinic.controller.DevelopmentController;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.dto.PatientDto;
import by.gp.clinic.enums.Gender;
import by.gp.clinic.enums.Speciality;
import by.gp.clinic.exception.DoctorExistsException;
import by.gp.clinic.exception.PatientExistsException;
import by.gp.clinic.exception.ShiftTimingNotExistsException;
import by.gp.clinic.service.DoctorService;
import by.gp.clinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static by.gp.clinic.enums.Gender.MALE;
import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class DevelopmentFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(DevelopmentFacade.class);

    private final static String ID = "ID";
    private final static String BIRTH_DATE = "BIRTH_DATE";
    private final static String GENDER = "GENDER";
    private final static String NAME = "NAME";
    private final static String LAST_NAME = "LAST";
    private final static String SPECIALITY = "SPECIALITY";
    private final static String DOCTOR_TEMPLATE = "<insert tableName=\"doctor\">\n" +
                                                  "            <column name=\"id\" value=\"" + ID + "\"/>\n" +
                                                  "            <column name=\"birth_date\" value=\"" + BIRTH_DATE +
                                                  "\"/>\n" +
                                                  "            <column name=\"gender\" value=\"" + GENDER + "\"/>\n" +
                                                  "            <column name=\"name\" value=\"" + NAME + "\"/>\n" +
                                                  "            <column name=\"last_name\" value=\"" + LAST_NAME +
                                                  "\"/>\n" +
                                                  "            <column name=\"speciality\" value=\"" + SPECIALITY +
                                                  "\"/>\n" +
                                                  "        </insert>\n";
    private final static String PATIENT_TEMPLATE = "<insert tableName=\"patient\">\n" +
                                                   "            <column name=\"id\" value=\"" + ID + "\"/>\n" +
                                                   "            <column name=\"birth_date\" value=\"" + BIRTH_DATE +
                                                   "\"/>\n" +
                                                   "            <column name=\"gender\" value=\"" + GENDER + "\"/>\n" +
                                                   "            <column name=\"name\" value=\"" + NAME + "\"/>\n" +
                                                   "            <column name=\"last_name\" value=\"" + LAST_NAME +
                                                   "\"/>\n" +
                                                   "        </insert>\n";


    private final DoctorFacade doctorFacade;
    private final PatientFacade patientFacade;
    private final DoctorService doctorService;
    private final PatientService patientService;

    private List<String> manNames;
    private List<String> womanNames;
    private List<String> lastNames;

    {
        manNames = getNames("static/EngMan.txt");
        womanNames = getNames("static/EngWoman.txt");
        lastNames = getNames("static/EngLastNames.txt");
    }

    private List<String> getNames(final String fileName) {
        try (Stream<String> lines = Files
            .lines(Paths.get(requireNonNull(DevelopmentController.class.getClassLoader().getResource(fileName))
                                 .toURI()))) {
            return lines.collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Error collecting names from " + fileName, e);
        }
        return null;
    }

    public void addPatients() {
        for (int i = 1; i < 101; i++) {
            final PatientDto patient = new PatientDto();
            patient.setId((long) i);
            patient.setBirthDate(getBirthDate());
            patient.setGender(getGender());
            patient.setName(getName(patient.getGender()));
            patient.setLastName(getName(lastNames));

            try {
                patientFacade.createPatient(patient);
            } catch (final PatientExistsException ignore) {
            }
        }
    }

    public void hireDoctors() throws ShiftTimingNotExistsException {
        for (final Speciality speciality : Speciality.values()) {
            for (int i = 0; i < 10; i++) {
                final DoctorDto doctor = new DoctorDto();
                doctor.setGender(getGender());
                doctor.setName(getName(doctor.getGender()));
                doctor.setLastName(getName(lastNames));
                doctor.setSpeciality(speciality);
                doctor.setBirthDate(getBirthDate());

                try {
                    doctorFacade.hireDoctor(doctor);
                } catch (final DoctorExistsException ignore) {
                }
            }
        }
    }

    private String getName(final Gender gender) {
        return gender == MALE ? getName(manNames) : getName(womanNames);
    }

    private Gender getGender() {
        return new Random().nextBoolean() ? Gender.FEMALE : MALE;
    }

    private LocalDate getBirthDate() {
        return LocalDate.now().minusYears((long) (Math.random() * 40) + 20);
    }

    private String getName(final List<String> names) {
        return names.get((int) (Math.random() * names.size()));
    }

    public String getFormattedDoctors() {
        final StringBuilder stringBuilder = new StringBuilder();
        doctorService.findAllDbo().forEach(
            d -> stringBuilder.append(
                DOCTOR_TEMPLATE.replace(ID, d.getId() + "").replace(BIRTH_DATE, d.getBirthDate() + "")
                    .replace(GENDER, d.getGender().name()).replace(NAME, d.getName())
                    .replace(LAST_NAME, d.getLastName())
                    .replace(SPECIALITY, d.getSpeciality().name())));
        return stringBuilder.toString();
    }

    public String getFormattedPAtients() {
        final StringBuilder stringBuilder = new StringBuilder();
        patientService.findAllDbo().forEach(
            d -> stringBuilder.append(
                PATIENT_TEMPLATE.replace(ID, d.getId() + "").replace(BIRTH_DATE, d.getBirthDate() + "")
                    .replace(GENDER, d.getGender().name()).replace(NAME, d.getName())
                    .replace(LAST_NAME, d.getLastName())));
        return stringBuilder.toString();
    }
}
