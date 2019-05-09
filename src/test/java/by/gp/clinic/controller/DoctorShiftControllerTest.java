package by.gp.clinic.controller;

import by.gp.clinic.dbo.AbstractDbo;
import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.mock.DoctorShiftMock;
import by.gp.clinic.repository.DoctorShiftRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DoctorShiftControllerTest extends AbstractControllerTest {

    private static final String SHIFT_URL = "/shift";

    @Autowired
    private DoctorShiftRepository doctorShiftRepository;

    @Test
    @SuppressWarnings("unchecked")
    public void getDoctorShifts() {
        final MvcResult result = getQuery(getUrl() + "/" + 1L);
        final Map<String, String> shifts = getObjectFromResult(result, HashMap.class);

        assertNotNull(shifts);
        assertEquals(10, shifts.size());
        assertEquals(200, result.getResponse().getStatus());
    }

    @Override
    protected JpaRepository<? extends AbstractDbo, Long> getRepository() {
        return doctorShiftRepository;
    }

    @Override
    protected DoctorShiftDto getDtoMock() {
        return DoctorShiftMock.getDoctorShiftDtoMock();
    }

    @Override
    protected String getUrl() {
        return SHIFT_URL;
    }
}