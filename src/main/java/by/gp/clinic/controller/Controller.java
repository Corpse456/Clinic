package by.gp.clinic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "Common Controller",
    description = "API methods to work with clinic server")
public class Controller {

    @GetMapping(value = "/info")
    @ApiOperation(value = "Get info")
    public String getCompanyPreview() {
        return "Jopa";
    }
}
