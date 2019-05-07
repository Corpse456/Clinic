package by.gp.clinic.service.schedule;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.exception.ShiftTimingNotExistsException;
import by.gp.clinic.service.DoctorService;
import by.gp.clinic.service.DoctorShiftService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeTableCreatingService implements WeeklyExecutable {

    private final DoctorShiftService doctorShiftService;
    private final DoctorService doctorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTableCreatingService.class);

    @Override
    public void execute() {
        final List<DoctorDbo> allDoctors = doctorService.findAllDbo();
        allDoctors.forEach(d -> {
            try {
                doctorShiftService.createTimeTableForWeekAfterNextWeek(d);
            } catch (final Exception e) {
                LOGGER.error("Error in Weekly executable", e);
            }
        });
    }
}
