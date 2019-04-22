package by.gp.clinic.controller;

import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.exception.DoctorExistsException;
import by.gp.clinic.exception.DoctorNotExistsException;
import by.gp.clinic.facade.DoctorFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
@Api(tags = "Doctor Controller",
    description = "API methods to work with doctors")
public class DoctorController {

    private final DoctorFacade doctorFacade;

    @PostMapping(value = "/hire")
    @ApiOperation(value = "Hire a new doctor")
    public String hireNewDoctor(@RequestBody DoctorDto doctor) throws DoctorExistsException {
        return new JSONObject().put("id", doctorFacade.hireDoctor(doctor)).toString();
    }

    @DeleteMapping(value = "/fire/{id}")
    @ApiOperation(value = "Fire a bad doctor")
    public void fireDoctor(@PathVariable("id") Long id) throws DoctorNotExistsException {
        doctorFacade.fireDoctor(id);
    }
}
