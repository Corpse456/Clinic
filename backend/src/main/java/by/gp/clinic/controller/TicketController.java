package by.gp.clinic.controller;

import by.gp.clinic.dto.PageDto;
import by.gp.clinic.dto.TicketDto;
import by.gp.clinic.exception.TicketAlreadyTakenException;
import by.gp.clinic.exception.WrongWorkingHoursException;
import by.gp.clinic.facade.TicketFacade;
import by.gp.clinic.search.TicketSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
@Api(tags = "Ticket Controller",
    value = "API methods for work with tickets")
public class TicketController {

    private final TicketFacade facade;

    @PostMapping
    @ApiOperation(value = "Add new ticket")
    public String addNewTicket(@RequestBody @Validated final TicketDto ticket)
        throws WrongWorkingHoursException, TicketAlreadyTakenException {
        return new JSONObject().put("id", facade.addTicket(ticket).getNumber()).toString();
    }

    @PostMapping(value = "/search")
    @ApiOperation(value = "Search tickets")
    public PageDto<TicketDto> searchTickets(@RequestBody final TicketSearchRequest searchRequest) {
        return facade.search(searchRequest);
    }
}
