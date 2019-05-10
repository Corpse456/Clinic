package by.gp.clinic.facade;

import by.gp.clinic.dbo.TicketDbo;
import by.gp.clinic.dto.TicketDto;
import by.gp.clinic.exception.TicketAlreadyTakenException;
import by.gp.clinic.exception.WrongWorkingHoursException;
import by.gp.clinic.service.DoctorShiftService;
import by.gp.clinic.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketFacade {

    private final TicketService ticketService;
    private final DoctorShiftService doctorShiftService;

    public TicketDbo addTicket(final TicketDto ticket) throws WrongWorkingHoursException, TicketAlreadyTakenException {
        if (!doctorShiftService.iaDoctorWorkingHours(ticket.getDoctorId(), ticket.getDateTime())) {
            throw new WrongWorkingHoursException(ticket.getDoctorId(), ticket.getDateTime());
        }
        if (ticketService.iaTimeBusy(ticket.getDoctorId(), ticket.getDateTime())) {
            throw new TicketAlreadyTakenException(ticket.getDateTime());
        }
        ticket.setNumber(ticketService.getNextNumber(ticket.getDoctorId(), ticket.getDateTime()));
        return ticketService.post(ticket);
    }
}
