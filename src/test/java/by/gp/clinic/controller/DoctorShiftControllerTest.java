package by.gp.clinic.controller;

import by.gp.clinic.dbo.AbstractDbo;
import by.gp.clinic.dto.AbstractDto;
import by.gp.clinic.mock.DoctorShiftMock;
import by.gp.clinic.repository.DoctorShiftRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class DoctorShiftControllerTest extends AbstractControllerTest {

    private static final String SHIFT_URL = "/shift";

    @Autowired
    private DoctorShiftRepository doctorShiftRepository;

    @Test
    public void getDoctorShifts() {
    }

    @Override
    protected JpaRepository<? extends AbstractDbo, Long> getRepository() {
        return doctorShiftRepository;
    }

    @Override
    protected AbstractDto getDtoMock() {
        return DoctorShiftMock.getDoctorDtoMock();
    }

    @Override
    protected String getUrl() {
        return SHIFT_URL;
    }
}