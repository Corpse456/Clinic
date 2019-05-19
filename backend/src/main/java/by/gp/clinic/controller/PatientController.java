package by.gp.clinic.controller;

import by.gp.clinic.dto.PageDto;
import by.gp.clinic.dto.PatientDto;
import by.gp.clinic.exception.EntityExistsException;
import by.gp.clinic.exception.EntityNotExistsException;
import by.gp.clinic.facade.PatientFacade;
import by.gp.clinic.search.PatientSearchRequest;
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
@RequestMapping("/patient")
@Api(tags = "Patient Controller",
    value = "API methods to work with patients")
public class PatientController {

    private final PatientFacade patientFacade;

    @PostMapping
    @ApiOperation(value = "Create a patient card")
    public String createPatient(@RequestBody @Validated PatientDto patient) throws EntityExistsException {
        return new JSONObject().put("id", patientFacade.createPatient(patient)).toString();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get info about patient")
    public PatientDto getPatient(@PathVariable("id") Long id) throws EntityNotExistsException {
        return patientFacade.getPatient(id);
    }

    @PostMapping(value = "/search")
    @ApiOperation(value = "Search patient")
    public PageDto<PatientDto> searchPatients(@RequestBody final PatientSearchRequest searchRequest) {
        return patientFacade.search(searchRequest);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Burn patient card")
    public void removePatient(@PathVariable("id") Long id) throws EntityNotExistsException {
        patientFacade.removePatient(id);
    }
}
