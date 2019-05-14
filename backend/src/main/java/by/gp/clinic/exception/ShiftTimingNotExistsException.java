package by.gp.clinic.exception;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ShiftTimingNotExistsException extends EntityNotExistsException {

    public ShiftTimingNotExistsException(final LocalTime startTime, final LocalTime endTime) {
        super("Shift timing from " + format(startTime) + " to " + format(endTime));
    }

    private static String format(final LocalTime startTime) {
        return DateTimeFormatter.ofPattern("HH:mm:ss").format(startTime);
    }
}
