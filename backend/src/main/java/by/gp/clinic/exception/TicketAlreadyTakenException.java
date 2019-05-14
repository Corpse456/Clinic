package by.gp.clinic.exception;

import by.gp.clinic.serializer.ClinicDateTimeSerializer;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketAlreadyTakenException extends BusinessLogicException {

    public TicketAlreadyTakenException(final LocalDateTime dateTime) {
        super(HttpStatus.BAD_REQUEST, "Ticket for time " + DateTimeFormatter
            .ofPattern(ClinicDateTimeSerializer.DATE_TIME_PATTERN).format(dateTime) + " already taken");
    }
}
