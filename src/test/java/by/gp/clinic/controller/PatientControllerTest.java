package by.gp.clinic.controller;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.dto.PatientDto;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.List;

import static by.gp.clinic.mock.PatientMock.getPatientDtoMock;
import static by.gp.clinic.serializer.ClinicDateTimeSerializer.DATE_TIME_PATTERN;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PatientControllerTest extends AbstractSpringMvcTest {

    private static final String PATIENT_URL = "/patient";
    private static final String SEARCH = "/search";

    @Test
    public void createPatientTest() {
        createPatient(getPatientDtoMock());
    }

    @Test
    public void getPatientTest() {
        final PatientDto patientMock = getPatientDtoMock();
        final Long id = createPatient(patientMock);
        final PatientDto savedPatient = getPatient(id);

        assertEquals(patientMock, savedPatient);
    }

    @Test
    public void removePatientTest() {
        final Long id = createPatient(getPatientDtoMock());
        deletePatient(id);
    }

    @Test
    public void findPatientsTest() {
        MvcResult result = getQuery(PATIENT_URL + SEARCH);
        final int beforeSize = getListOfObjectsFromResult(result, new TypeReference<List<PatientDto>>() {
        }).size();
        createPatient(getPatientDtoMock());
        createPatient(getPatientDtoMock());
        createPatient(getPatientDtoMock());

        result = getQuery(PATIENT_URL + SEARCH);
        final List<PatientDto> list = getListOfObjectsFromResult(result, new TypeReference<List<PatientDto>>() {
        });
        assertEquals(beforeSize + 3, list.size());
    }

    @Test
    public void createPatientTwiceTest() {
        final PatientDto patient = getPatientDtoMock();
        createPatient(patient);

        final MvcResult result = postQuery(PATIENT_URL, patient);
        final JSONObject answer = getJsonFormString(getContent(result));

        assertEquals(400, result.getResponse().getStatus());
        assertNotNull(LocalDateTime.parse(getStringFromJson(answer, "time"), ofPattern(DATE_TIME_PATTERN)));
        assertNotNull(getStringFromJson(answer, "message"));
    }

    private Long createPatient(final PatientDto patient) {
        final MvcResult result = postQuery(PATIENT_URL, patient);

        final JSONObject answer = getJsonFormString(getContent(result));
        final Long id = getLongFromJson(answer, "id");

        assertNotNull(id);
        assertEquals(200, result.getResponse().getStatus());
        return id;
    }

    private PatientDto getPatient(final Long id) {
        final MvcResult result = getQuery(PATIENT_URL + "/" + id);

        final PatientDto patient = getObjectFromResult(result, PatientDto.class);
        assertNotNull(patient);
        assertEquals(200, result.getResponse().getStatus());
        return patient;
    }

    private void deletePatient(final Long id) {
        final MvcResult result = deleteQuery(PATIENT_URL + "/" + id);

        assertEquals(200, result.getResponse().getStatus());
    }
}