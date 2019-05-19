package by.gp.clinic.controller;

import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.dto.PageDto;
import by.gp.clinic.exception.EntityExistsException;
import by.gp.clinic.exception.EntityNotExistsException;
import by.gp.clinic.exception.ShiftTimingNotExistsException;
import by.gp.clinic.facade.DoctorFacade;
import by.gp.clinic.search.DoctorSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
@Api(tags = "Doctor Controller",
    value = "API methods to work with doctors")
public class DoctorController {

    private final DoctorFacade doctorFacade;

    @PostMapping
    @ApiOperation(value = "Hire a new doctor")
    public String hireNewDoctor(@RequestBody @Validated DoctorDto doctor)
        throws EntityExistsException, ShiftTimingNotExistsException {
        return new JSONObject().put("id", doctorFacade.hireDoctor(doctor)).toString();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get info about doctor")
    public DoctorDto getDoctor(@PathVariable("id") Long id) throws EntityNotExistsException {
        return doctorFacade.getDoctor(id);
    }

    @PostMapping(value = "/search")
    @ApiOperation(value = "Search doctors")
    public PageDto<DoctorDto> searchDoctors(@RequestBody final DoctorSearchRequest searchRequest) {
        return doctorFacade.search(searchRequest);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Fire a bad doctor")
    public void fireDoctor(@PathVariable("id") Long id) throws EntityNotExistsException {
        doctorFacade.fireDoctor(id);
    }
}
