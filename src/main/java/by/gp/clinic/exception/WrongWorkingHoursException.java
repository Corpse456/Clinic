package by.gp.clinic.exception;

import by.gp.clinic.serializer.ClinicDateTimeSerializer;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WrongWorkingHoursException extends BusinessLogicException {

    public WrongWorkingHoursException(final Long id, final LocalDateTime dateTime) {
        super(HttpStatus.BAD_REQUEST, "Doctor with id = " + id + " doesn't work on " + DateTimeFormatter
            .ofPattern(ClinicDateTimeSerializer.DATE_TIME_PATTERN).format(dateTime));
    }
}
