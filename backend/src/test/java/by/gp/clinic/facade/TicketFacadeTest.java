package by.gp.clinic.facade;

import by.gp.clinic.dto.TicketDto;
import by.gp.clinic.exception.TicketAlreadyTakenException;
import by.gp.clinic.exception.WrongWorkingHoursException;
import by.gp.clinic.service.DoctorShiftService;
import by.gp.clinic.service.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static by.gp.clinic.mock.TicketMock.getTicketDtoMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class TicketFacadeTest {

    @InjectMocks
    private TicketFacade ticketFacade;
    @Mock
    private DoctorShiftService doctorShiftService;
    @Mock
    private TicketService ticketService;

    @Test(expected = WrongWorkingHoursException.class)
    public void addTicketWrongDoctorHours() throws WrongWorkingHoursException, TicketAlreadyTakenException {
        final TicketDto ticket = getTicketDtoMock();
        doReturn(false).when(doctorShiftService).isDoctorWorkingHours(any(), any());
        ticketFacade.addTicket(ticket);
    }

    @Test(expected = TicketAlreadyTakenException.class)
    public void addTicketTimeBusy() throws WrongWorkingHoursException, TicketAlreadyTakenException {
        doReturn(true).when(doctorShiftService).isDoctorWorkingHours(any(), any());
        doReturn(true).when(ticketService).isTimeBusy(any(), any());
        ticketFacade.addTicket(getTicketDtoMock());
    }
}
