package by.gp.clinic.service;

import by.gp.clinic.dto.CredentialsDto;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.dto.PatientDto;
import by.gp.clinic.dto.SpecialityDto;
import by.gp.clinic.dto.TicketDto;
import by.gp.clinic.enumerated.Gender;
import by.gp.clinic.enumerated.UserRole;
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
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static by.gp.clinic.enumerated.Gender.MALE;
import static java.time.LocalDateTime.now;

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

    private List<String> getNames(final String fileName) {
        var in = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName))));
        try {
            return IOUtils.readLines(in);
        } catch (Exception e) {
            LOGGER.error("Error collecting names from {}", fileName, e);
        }
        return null;
    }

    public void addPatients() {
        for (var i = 1; i < 101; i++) {
            final var patient = new PatientDto();
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
        final var all = specialityService.findAll();
        all.forEach(this::addDoctors);
    }

    public void addTickets() {
        final var patients = patientFacade.search(new PatientSearchRequest()).getElements();
        final var doctors = (List<DoctorDto>) doctorFacade.search(new DoctorSearchRequest(), UserRole.USER).getElements();

        patients.forEach(p -> {
            for (var i = 0; i < 50; i++) {
                final var ticketDto = new TicketDto();
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
        for (var i = 0; i < 10; i++) {
            final var doctor = new DoctorDto();
            doctor.setGender(getGender());
            doctor.setName(getName(doctor.getGender()));
            doctor.setLastName(getName(getLastNames()));
            doctor.setSpecialityId(speciality.getId());
            doctor.setBirthDate(getBirthDate());
            try {
                doctorFacade.hireDoctor(doctor);
            } catch (final DoctorExistsException | ShiftTimingNotExistsException ignore) {
            }
        }
    }

    public void addUser() throws PatientNotExistsException, DoctorNotExistsException, UserExistsException {
        final var patient = patientFacade.getPatient(17L);
        final var user = new CredentialsDto();
        user.setName(patient.getName());
        user.setLastName(patient.getLastName());
        user.setAlias("user");
        user.setPassword("password");

        userFacade.createUser(user);
    }

    private String getName(final Gender gender) {
        return gender == MALE ? getName(getManNames()) : getName(getWomanNames());
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

    private List<String> getManNames() {
        if (manNames == null) {
            manNames = getNames("static/EngMan.txt");
        }
        return manNames;
    }

    private List<String> getWomanNames() {
        if (womanNames == null) {
            womanNames = getNames("static/EngWoman.txt");
        }
        return womanNames;
    }

    private List<String> getLastNames() {
        if (lastNames == null) {
            lastNames = getNames("static/EngLastNames.txt");
        }
        return lastNames;
    }
}
