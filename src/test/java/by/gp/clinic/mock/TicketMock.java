package by.gp.clinic.mock;

import by.gp.clinic.dto.TicketDto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static java.time.temporal.TemporalAdjusters.next;

public class TicketMock {

    public static TicketDto getTicketDtoMock() {
        final TicketDto ticketDto = new TicketDto();
        ticketDto.setDoctorId(1L);
        ticketDto.setPatientId(1L);
        ticketDto.setDateTime(LocalDateTime.now().withHour(12).withMinute(15).with(next(DayOfWeek.MONDAY)));
        return ticketDto;
    }
}
