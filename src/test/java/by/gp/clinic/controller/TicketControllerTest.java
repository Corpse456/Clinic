package by.gp.clinic.controller;

import by.gp.clinic.dto.TicketDto;
import by.gp.clinic.repository.TicketRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static by.gp.clinic.mock.TicketMock.getTicketDtoMock;

public class TicketControllerTest extends AbstractControllerTest {

    private static final String TICKET_URL = "/ticket";

    @Autowired
    private TicketRepository repository;

    @Test
    public void createTicketTest() {
        addEntityCheck();
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