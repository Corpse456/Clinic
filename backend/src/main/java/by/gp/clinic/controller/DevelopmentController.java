package by.gp.clinic.controller;

import by.gp.clinic.exception.DoctorNotExistsException;
import by.gp.clinic.exception.PatientNotExistsException;
import by.gp.clinic.exception.UserExistsException;
import by.gp.clinic.service.DevelopmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/development")
@Tag(name = "Development Controller",
    description = "API methods for test setup")
public class DevelopmentController {

    private final DevelopmentService developmentService;

    @PostMapping(value = "/doctors")
    @Operation(summary = "Hire doctors")
    public void hireDoctors() {
        developmentService.hireDoctors();
    }

    @PostMapping(value = "/patients")
    @Operation(summary = "Create patients")
    public void createPatients() {
        developmentService.addPatients();
    }

    @PostMapping(value = "/tickets")
    @Operation(summary = "Create random tickets")
    public void createTickets() {
        developmentService.addTickets();
    }

    @PostMapping(value = "/fill")
    @Operation(summary = "Fill the data")
    public void fillData() throws PatientNotExistsException, DoctorNotExistsException, UserExistsException {
        developmentService.hireDoctors();
        developmentService.addPatients();
        developmentService.addTickets();
        developmentService.addUser();
    }
}
