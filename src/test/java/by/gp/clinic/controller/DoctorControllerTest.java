package by.gp.clinic.controller;

import by.gp.clinic.AbstractTest;
import by.gp.clinic.dto.DoctorDto;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.test.web.servlet.MvcResult;

import static by.gp.clinic.mock.DoctorMock.getDoctorDtoMock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DoctorControllerTest extends AbstractTest {

    private static final String DOCTOR_URL = "/doctor";

    @Test
    public void A_hireDoctorTest() {
        hireDoctor(getDoctorDtoMock());
    }

    @Test
    public void С_getDoctorTest() {
        final DoctorDto doctorMock = getDoctorDtoMock();
        final Long id = hireDoctor(doctorMock);
        final DoctorDto savedDoctor = getDoctor(id);

        assertEquals(doctorMock, savedDoctor);
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
}