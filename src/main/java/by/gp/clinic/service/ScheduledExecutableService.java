package by.gp.clinic.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledExecutableService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledExecutableService.class);

    private final List<WeeklyExecutable> weeklyExecutable;

    @Scheduled(cron = "${clinic.cron.weekly}")
    public void performDailyExecuting() {
        weeklyExecutable.forEach(dailyExecutable -> {
            try {
                dailyExecutable.execute();
            } catch (final Exception ex) {
                LOGGER.error("Error on weekly execution", ex);
            }
        });
    }
}
