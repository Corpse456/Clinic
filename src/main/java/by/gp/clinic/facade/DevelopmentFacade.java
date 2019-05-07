package by.gp.clinic.facade;

import by.gp.clinic.controller.DevelopmentController;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.enums.Gender;
import by.gp.clinic.enums.Speciality;
import by.gp.clinic.exception.DoctorExistsException;
import by.gp.clinic.exception.ShiftTimingNotExistsException;
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

    private final DoctorFacade doctorFacade;

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

    public void hireDoctors() throws ShiftTimingNotExistsException {
        for (final Speciality speciality : Speciality.values()) {
            for (int i = 0; i < 10; i++) {
                final DoctorDto doctor = new DoctorDto();
                doctor.setGender(new Random().nextBoolean() ? Gender.FEMALE : MALE);
                doctor.setName(doctor.getGender() == MALE ? getName(manNames) : getName(womanNames));
                doctor.setLastName(getName(lastNames));
                doctor.setSpeciality(speciality);
                doctor.setBirthDate(LocalDate.now().minusYears((long) (Math.random() * 40) + 20));

                try {
                    doctorFacade.hireDoctor(doctor);
                } catch (final DoctorExistsException ignore) {
                }
            }
        }
    }

    private String getName(final List<String> names) {
        return names.get((int) (Math.random() * names.size()));
    }
}
