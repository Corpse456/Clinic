package by.gp.clinic.controller;

import by.gp.clinic.dto.TicketDto;
import by.gp.clinic.facade.TicketFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
@Api(tags = "Ticket Controller",
    description = "API methods for work with tickets")
public class TicketController {

    private final TicketFacade facade;

    @PostMapping
    @ApiOperation(value = "Add new ticket")
    public String getDoctorShifts(@RequestBody final TicketDto ticket) {
        return new JSONObject().put("number", facade.addTicket(ticket).getNumber()).toString();
    }
}