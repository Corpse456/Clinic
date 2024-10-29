package by.gp.clinic.controller;

import by.gp.clinic.dto.AbstractDto;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.dto.PageDto;
import by.gp.clinic.repository.DoctorRepository;
import by.gp.clinic.search.DoctorSearchRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static by.gp.clinic.mock.DoctorMock.getDoctorDtoMock;

public class DoctorControllerTest extends AbstractControllerTest {

    private static final String DOCTOR_URL = "/doctor";

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void createDoctorTest() {
        addEntity(getDtoMock(), "/admin" + getUrl());
    }

    @Test
    public void getDoctorTest() {
        getEntityTest(DoctorDto.class, 2L);
    }

    @Test
    public void removeDoctorTest() {
        removeEntityTest(2L);
    }

    @Test
    public void findDoctorsTest() {
        findEntitiesTest(new DoctorSearchRequest(), new TypeReference<PageDto<DoctorDto>>() {});
    }

    @Override
    protected DoctorRepository getRepository() {
        return doctorRepository;
    }

    @Override
    protected AbstractDto getDtoMock() {
        return getDoctorDtoMock();
    }

    @Override
    protected String getUrl() {
        return DOCTOR_URL;
    }
}
