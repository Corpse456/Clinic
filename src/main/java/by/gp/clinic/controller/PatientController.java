package by.gp.clinic.controller;

import by.gp.clinic.dto.PatientDto;
import by.gp.clinic.exception.PatientExistsException;
import by.gp.clinic.exception.PatientNotExistsException;
import by.gp.clinic.facade.PatientFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
@Api(tags = "Patient Controller",
    description = "API methods to work with patients")
public class PatientController {

    private final PatientFacade patientFacade;

    @PostMapping
    @ApiOperation(value = "Create a patient card")
    public String createPatient(@RequestBody PatientDto patient) throws PatientExistsException {
        return new JSONObject().put("id", patientFacade.createPatient(patient)).toString();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get info about patient")
    public PatientDto getPatient(@PathVariable("id") Long id) throws PatientNotExistsException {
        return patientFacade.getPatient(id);
    }

    @GetMapping(value = "/search")
    @ApiOperation(value = "Search patient")
    public List<PatientDto> searchPatients() {
        return patientFacade.findAll();
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Burn patient card")
    public void removePatient(@PathVariable("id") Long id) throws PatientNotExistsException {
        patientFacade.removePatient(id);
    }
}
