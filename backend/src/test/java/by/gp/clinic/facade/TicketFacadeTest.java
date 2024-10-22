package by.gp.clinic.facade;

import by.gp.clinic.exception.TicketAlreadyTakenException;
import by.gp.clinic.exception.WrongWorkingHoursException;
import by.gp.clinic.service.DoctorShiftService;
import by.gp.clinic.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static by.gp.clinic.mock.TicketMock.getTicketDtoMock;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class TicketFacadeTest {

    @InjectMocks
    private TicketFacade ticketFacade;

    @Mock
    private DoctorShiftService doctorShiftService;

    @Mock
    private TicketService ticketService;

    @Test
    public void addTicketWrongDoctorHours() throws WrongWorkingHoursException, TicketAlreadyTakenException {
        assertThrows(WrongWorkingHoursException.class, () -> {
            final var ticket = getTicketDtoMock();
            doReturn(false).when(doctorShiftService).isDoctorWorkingHours(any(), any());
            ticketFacade.addTicket(ticket);
        });
    }

    @Test
    public void addTicketTimeBusy() throws WrongWorkingHoursException, TicketAlreadyTakenException {
        assertThrows(TicketAlreadyTakenException.class, () -> {
            doReturn(true).when(doctorShiftService).isDoctorWorkingHours(any(), any());
            doReturn(true).when(ticketService).isTimeBusy(any(), any());
            ticketFacade.addTicket(getTicketDtoMock());
        });
    }
}
