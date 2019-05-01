package by.gp.clinic.controller;

import by.gp.clinic.facade.DevelopmentFacade;
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
    description = "API methods for test setup")
public class DevelopmentController {

    private final DevelopmentFacade developmentFacade;

    @PostMapping
    @ApiOperation(value = "Hire doctors")
    public void hireDoctors() {
        developmentFacade.hireDoctors();
    }
}
