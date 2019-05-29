package by.gp.clinic.controller;

import by.gp.clinic.exception.DoctorNotExistsException;
import by.gp.clinic.exception.PatientNotExistsException;
import by.gp.clinic.exception.UserExistsException;
import by.gp.clinic.service.DevelopmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/development")
@Api(tags = "Development Controller",
    value = "API methods for test setup")
public class DevelopmentController {

    private final DevelopmentService developmentService;

    @PostMapping(value = "/doctors")
    @ApiOperation(value = "Hire doctors")
    public void hireDoctors() {
        developmentService.hireDoctors();
    }

    @PostMapping(value = "/patients")
    @ApiOperation(value = "Create patients")
    public void createPatients() {
        developmentService.addPatients();
    }

    @PostMapping(value = "/tickets")
    @ApiOperation(value = "Create random tickets")
    public void createTickets() {
        developmentService.addTickets();
    }

    @PostMapping(value = "/fill")
    @ApiOperation(value = "Fill the data")
    public void fillData() throws PatientNotExistsException, DoctorNotExistsException, UserExistsException {
        developmentService.hireDoctors();
        developmentService.addPatients();
        developmentService.addTickets();
        developmentService.addUser();
    }
}
