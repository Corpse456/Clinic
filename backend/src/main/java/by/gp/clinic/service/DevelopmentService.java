package by.gp.clinic.service;

import by.gp.clinic.controller.DevelopmentController;
import by.gp.clinic.dto.CredentialsDto;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.dto.PatientDto;
import by.gp.clinic.dto.SpecialityDto;
import by.gp.clinic.dto.TicketDto;
import by.gp.clinic.enumerated.Gender;
import by.gp.clinic.exception.DoctorExistsException;
import by.gp.clinic.exception.DoctorNotExistsException;
import by.gp.clinic.exception.PatientExistsException;
import by.gp.clinic.exception.PatientNotExistsException;
import by.gp.clinic.exception.ShiftTimingNotExistsException;
import by.gp.clinic.exception.UserExistsException;
import by.gp.clinic.facade.DoctorFacade;
import by.gp.clinic.facade.PatientFacade;
import by.gp.clinic.facade.TicketFacade;
import by.gp.clinic.facade.UserFacade;
import by.gp.clinic.search.DoctorSearchRequest;
import by.gp.clinic.search.PatientSearchRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static by.gp.clinic.enumerated.Gender.MALE;
import static java.time.LocalDateTime.now;
import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class DevelopmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DevelopmentService.class);

    private final SpecialityService specialityService;
    private final PatientFacade patientFacade;
    private final DoctorFacade doctorFacade;
    private final TicketFacade ticketFacade;
    private final UserFacade userFacade;

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

    public void hireDoctors() {
        final List<SpecialityDto> all = specialityService.findAll();
        all.forEach(this::addDoctors);
    }

    public void addTickets() {
        final Collection<PatientDto> patients = patientFacade.search(new PatientSearchRequest()).getElements();
        final List<DoctorDto> doctors = (List<DoctorDto>) doctorFacade.search(new DoctorSearchRequest()).getElements();

        patients.forEach(p -> {
            for (int i = 0; i < 50; i++) {
                final TicketDto ticketDto = new TicketDto();
                ticketDto.setDoctorId(doctors.get((int) (doctors.size() * Math.random())).getId());
                ticketDto.setPatientId(p.getId());
                ticketDto.setDateTime(now().plusHours((long) (Math.random() * 200) + 2).withMinute(15).withSecond(0));
                try {
                    ticketFacade.addTicket(ticketDto);
                } catch (Exception ignore) {
                }
            }
        });
    }

    private void addDoctors(final SpecialityDto speciality) {
        for (int i = 0; i < 10; i++) {
            final DoctorDto doctor = new DoctorDto();
            doctor.setGender(getGender());
            doctor.setName(getName(doctor.getGender()));
            doctor.setLastName(getName(lastNames));
            doctor.setSpecialityId(speciality.getId());
            doctor.setBirthDate(getBirthDate());
            try {
                doctorFacade.hireDoctor(doctor);
            } catch (final DoctorExistsException | ShiftTimingNotExistsException ignore) {
            }
        }
    }

    public void addUser() throws PatientNotExistsException, DoctorNotExistsException, UserExistsException {
        final PatientDto patient = patientFacade.getPatient(17L);
        final CredentialsDto user = new CredentialsDto();
        user.setName(patient.getName());
        user.setLastName(patient.getLastName());
        user.setAlias("user");
        user.setPassword("password");

        userFacade.createUser(user);
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
}
