package by.gp.clinic.controller;

import by.gp.clinic.dto.TicketDto;
import by.gp.clinic.exception.ShiftTimingNotExistsException;
import by.gp.clinic.facade.DoctorFacade;
import by.gp.clinic.repository.TicketRepository;
import by.gp.clinic.service.DoctorService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static by.gp.clinic.mock.TicketMock.getTicketDtoMock;

public class TicketControllerTest extends AbstractControllerTest {

    private static final String TICKET_URL = "/ticket";

    @Autowired
    private DoctorFacade doctorFacade;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private TicketRepository repository;

    @Test
    public void createTicketTest() throws ShiftTimingNotExistsException {
        doctorFacade.addNewDoctorToTimeTable(doctorService.getDbo(1L));
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