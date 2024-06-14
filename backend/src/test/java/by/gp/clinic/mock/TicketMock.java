package by.gp.clinic.mock;

import by.gp.clinic.dbo.TicketDbo;
import by.gp.clinic.dto.TicketDto;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class TicketMock {

    public static TicketDto getTicketDtoMock() {
        final var ticket = new TicketDto();
        ticket.setId(1L);
        ticket.setDoctorId(1L);
        ticket.setPatientId(1L);
        ticket.setDateTime(getDateTime());
        return ticket;
    }

    private static LocalDateTime getDateTime() {
        return now().plusDays(1).withHour(12).withMinute(15).withSecond(0);
    }

    public static TicketDbo getTicketDboMock() {
        final var ticket = new TicketDbo();
        ticket.setId(1L);
        ticket.setNumber(1);
        ticket.setDoctor(DoctorMock.getDoctorDboMock());
        ticket.setPatient(PatientMock.getPatientDboMock());
        ticket.setDateTime(getDateTime());
        return ticket;
    }
}
