package by.gp.clinic.controller;

import by.gp.clinic.dbo.AbstractDbo;
import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.dto.SpecialDoctorShiftDto;
import by.gp.clinic.mock.DoctorShiftMock;
import by.gp.clinic.repository.DoctorShiftRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static by.gp.clinic.mock.SpecialDoctorShiftMock.getSpecialDoctorShiftSpecialityDtoMock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DoctorShiftControllerTest extends AbstractControllerTest {

    private static final String SHIFT_URL = "/shift";
    private static final String SPECIAL_URL = "/special";
    public static final String ERROR_MESSAGE = "one of property must be not null (doctorId / speciality)";

    @Autowired
    private DoctorShiftRepository doctorShiftRepository;

    @Test
    public void postShiftForDateTest() {
        addEntityWithoutAnswer();
    }

    @Test
    public void getDoctorShifts() {
        final MvcResult result = getQuery(getUrl() + "/" + 1L);
        final Map shifts = getObjectFromResult(result, HashMap.class);

        assertNotNull(shifts);
        assertEquals(10, shifts.size());
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void addSpecialShiftTest() {
        addEntityWithoutAnswer(getSpecialDoctorShiftSpecialityDtoMock(), SPECIAL_URL);
    }

    @Test
    public void addSpecialShiftWithEmptyDoctorAndSpecialityTest() {
        final SpecialDoctorShiftDto specialShift = getSpecialDoctorShiftSpecialityDtoMock();
        specialShift.setSpecialityId(null);
        addEntityWithStatus(specialShift, 400, ERROR_MESSAGE, SPECIAL_URL);
    }

    @Override
    protected JpaRepository<? extends AbstractDbo, Long> getRepository() {
        return doctorShiftRepository;
    }

    @Override
    protected DoctorShiftDto getDtoMock() {
        return DoctorShiftMock.getDoctorShiftNotExistsShiftTimingDtoMock();
    }

    @Override
    protected String getUrl() {
        return SHIFT_URL;
    }
}