package by.gp.clinic.controller;

import by.gp.clinic.dto.AbstractDto;
import by.gp.clinic.dto.PatientDto;
import by.gp.clinic.repository.PatientRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.List;

import static by.gp.clinic.mock.PatientMock.getPatientDtoMock;
import static by.gp.clinic.serializer.ClinicDateTimeSerializer.DATE_TIME_PATTERN;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PatientControllerTest extends AbstractControllerTest {

    private static final String PATIENT_URL = "/patient";

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void createPatientTest() {
        addEntity();
    }

    @Test
    public void getPatientTest() {
        getEntityTest(PatientDto.class, 2L);
    }

    @Test
    public void removePatientTest() {
        removeEntityTest(1L);
    }

    @Test
    public void findPatientsTest() {
        findEntitiesTest(new TypeReference<List<PatientDto>>() {
        });
    }

    @Test
    public void createPatientTwiceTest() {
        final PatientDto patient = getPatientDtoMock();
        addEntity(patient);

        final MvcResult result = postQuery(PATIENT_URL, patient);
        final JSONObject answer = getJsonFormString(getContentAsString(result));

        assertEquals(400, result.getResponse().getStatus());
        assertNotNull(LocalDateTime.parse(getStringFromJson(answer, "time"), ofPattern(DATE_TIME_PATTERN)));
        assertNotNull(getStringFromJson(answer, "message"));
    }

    @Override
    protected String getUrl() {
        return PATIENT_URL;
    }

    @Override
    protected PatientRepository getRepository() {
        return patientRepository;
    }

    @Override
    protected AbstractDto getDtoMock() {
        return getPatientDtoMock();
    }
}