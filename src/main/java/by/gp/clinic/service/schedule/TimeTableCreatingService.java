package by.gp.clinic.service.schedule;

import by.gp.clinic.exception.ShiftTimingNotExistsException;
import by.gp.clinic.facade.DevelopmentFacade;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeTableCreatingService implements WeeklyExecutable {

    private final DevelopmentFacade developmentFacade;

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeTableCreatingService.class);

    @Override
    public void execute() {
        try {
            developmentFacade.hireDoctors();
        } catch (final ShiftTimingNotExistsException e) {
            LOGGER.error("Error in Weekly executable", e);
        }
    }
}
