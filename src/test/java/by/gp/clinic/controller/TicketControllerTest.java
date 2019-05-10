package by.gp.clinic.controller;

import by.gp.clinic.dto.TicketDto;
import by.gp.clinic.repository.TicketRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static by.gp.clinic.mock.TicketMock.getTicketDtoMock;

public class TicketControllerTest extends AbstractControllerTest {

    private static final String TICKET_URL = "/ticket";

    @Autowired
    private TicketRepository repository;

    @Test
    public void createTicketTest() {
        addEntityWithStatus();
    }

    @Test
    public void addTicketInPast() {
        final TicketDto ticketDtoMock = getDtoMock();
        ticketDtoMock.setDateTime(LocalDateTime.now().minusHours(1).withMinute(15));
        addEntityWithStatus(ticketDtoMock, 400, "Date must be in future");
    }

    @Test
    public void addTicketWrongTime() {
        final TicketDto ticketDtoMock = getDtoMock();
        ticketDtoMock.setDateTime(ticketDtoMock.getDateTime().withMinute(13));
        addEntityWithStatus(ticketDtoMock, 400, "Ticket date must be a multiple of 15");
    }

    @Override
    protected String getUrl() {
        return TICKET_URL;
    }

    @Override
    protected TicketRepository getRepository() {
        return repository;
    }

    @Override
    protected TicketDto getDtoMock() {
        return getTicketDtoMock();
    }
}