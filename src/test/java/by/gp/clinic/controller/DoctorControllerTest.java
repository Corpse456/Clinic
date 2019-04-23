package by.gp.clinic.controller;

import by.gp.clinic.AbstractTest;
import by.gp.clinic.dto.DoctorDto;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;

import static by.gp.clinic.mock.DoctorMock.getDoctorDtoMock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DoctorControllerTest extends AbstractTest {

    private static final String DOCTOR_HIRE_URL = "doctor/hire";

    @Test
    public void hireDoctorTest() {
        final DoctorDto doctor = getDoctorDtoMock();
        final MvcResult result = postQuery(DOCTOR_HIRE_URL, doctor);

        final HashMap answer = fromJson(getContent(result));
        assertNotNull(answer.get("id"));
        assertEquals(200, result.getResponse().getStatus());
    }
}