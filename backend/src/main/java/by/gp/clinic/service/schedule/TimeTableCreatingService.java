package by.gp.clinic.service.schedule;

import by.gp.clinic.service.DoctorService;
import by.gp.clinic.service.DoctorShiftService;
import by.gp.clinic.service.SpecialDoctorShiftService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeTableCreatingService implements WeeklyExecutable {

    private final SpecialDoctorShiftService specialDoctorShiftService;
    private final DoctorShiftService doctorShiftService;
    private final DoctorService doctorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTableCreatingService.class);

    @Override
    public void execute() {
        final var allDoctors = doctorService.findAllDbo();
        allDoctors.forEach(d -> {
            try {
                final var specialShifts =
                    specialDoctorShiftService.getSpecialShifts(d.getId(), d.getSpeciality().getId());
                doctorShiftService.createTimeTableForWeekAfterNextWeek(d, specialShifts);
            } catch (final Exception e) {
                LOGGER.error("Error in Weekly executable", e);
            }
        });
    }
}
