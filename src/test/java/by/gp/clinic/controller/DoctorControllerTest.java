package by.gp.clinic.controller;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.dto.DoctorDto;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static by.gp.clinic.mock.DoctorMock.getDoctorDtoMock;
import static by.gp.clinic.serializer.ClinicDateTimeSerializer.DATE_TIME_PATTERN;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DoctorControllerTest extends AbstractSpringMvcTest {

    private static final String DOCTOR_URL = "/doctor";

    @Test
    public void hireDoctorTest() {
        hireDoctor(getDoctorDtoMock());
    }

    @Test
    public void getDoctorTest() {
        final DoctorDto doctorMock = getDoctorDtoMock();
        final Long id = hireDoctor(doctorMock);
        final DoctorDto savedDoctor = getDoctor(id);

        assertEquals(doctorMock, savedDoctor);
    }

    @Test
    public void fireDoctorTest() {
        final Long id = hireDoctor(getDoctorDtoMock());
        deleteDoctor(id);
    }

    @Test
    public void hireDoctorTwiceTest() {
        final DoctorDto doctor = getDoctorDtoMock();
        hireDoctor(doctor);

        final MvcResult result = postQuery(DOCTOR_URL, doctor);
        final JSONObject answer = getJsonFormString(getContent(result));

        assertEquals(400, result.getResponse().getStatus());
        assertNotNull(LocalDateTime.parse(getStringFromJson(answer, "time"), ofPattern(DATE_TIME_PATTERN)));
        assertNotNull(getStringFromJson(answer, "message"));
    }

    private Long hireDoctor(final DoctorDto doctor) {
        final MvcResult result = postQuery(DOCTOR_URL, doctor);

        final JSONObject answer = getJsonFormString(getContent(result));
        final Long id = getLongFromJson(answer, "id");

        assertNotNull(id);
        assertEquals(200, result.getResponse().getStatus());
        return id;
    }

    private DoctorDto getDoctor(final Long id) {
        final MvcResult result = getQuery(DOCTOR_URL + "/" + id);

        final DoctorDto doctor = getObjectFromResult(result, DoctorDto.class);
        assertNotNull(doctor);
        assertEquals(200, result.getResponse().getStatus());
        return doctor;
    }

    private void deleteDoctor(final Long id) {
        final MvcResult result = deleteQuery(DOCTOR_URL + "/" + id);

        assertEquals(200, result.getResponse().getStatus());
    }
}