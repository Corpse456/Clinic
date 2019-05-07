package by.gp.clinic.facade;

import by.gp.clinic.dbo.TicketDbo;
import by.gp.clinic.dto.TicketDto;
import by.gp.clinic.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketFacade {

    private final TicketService ticketService;

    public TicketDbo addTicket(final TicketDto ticket) {
        ticket.setNumber(ticketService.getNextNumber(ticket.getDoctor().getId(), ticket.getDateTime()));
        return ticketService.post(ticket);
    }
}
