package by.gp.clinic.controller;

import by.gp.clinic.exception.ShiftTimingNotExistsException;
import by.gp.clinic.facade.DevelopmentFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/development")
@Api(tags = "Development Controller",
    description = "API methods for test setup")
public class DevelopmentController {

    private final DevelopmentFacade developmentFacade;

    @PostMapping(value = "/doctors")
    @ApiOperation(value = "Hire doctors")
    public void hireDoctors() throws ShiftTimingNotExistsException {
        developmentFacade.hireDoctors();
    }

    @PostMapping(value = "/patients")
    @ApiOperation(value = "Create patients")
    public void createPatients() {
        developmentFacade.addPatients();
    }

    @GetMapping(value = "/doctors")
    @ApiOperation(value = "Get formatted doctors")
    public String getFormattedDoctors() {
        return developmentFacade.getFormattedDoctors();
    }

    @GetMapping(value = "/patients")
    @ApiOperation(value = "Get formatted patients")
    public String getFormattedPatients() {
        return developmentFacade.getFormattedPAtients();
    }
}
