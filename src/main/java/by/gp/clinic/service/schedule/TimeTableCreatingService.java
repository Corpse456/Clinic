package by.gp.clinic.service.schedule;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.service.DoctorService;
import by.gp.clinic.service.DoctorShiftService;
import by.gp.clinic.service.SpecialDoctorShiftService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TimeTableCreatingService implements WeeklyExecutable {

    private final SpecialDoctorShiftService specialDoctorShiftService;
    private final DoctorShiftService doctorShiftService;
    private final DoctorService doctorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTableCreatingService.class);

    @Override
    public void execute() {
        final List<DoctorDbo> allDoctors = doctorService.findAllDbo();
        allDoctors.forEach(d -> {
            try {
                final Map<DayOfWeek, ShiftTimingDbo> specialShifts =
                    specialDoctorShiftService.getSpecialShifts(d.getId(), d.getSpeciality());

                doctorShiftService.createTimeTableForWeekAfterNextWeek(d, specialShifts);
            } catch (final Exception e) {
                LOGGER.error("Error in Weekly executable", e);
            }
        });
    }
}
