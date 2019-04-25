package by.gp.clinic.controller;

import by.gp.clinic.dto.AbstractDto;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.repository.DoctorRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.gp.clinic.mock.DoctorMock.getDoctorDtoMock;

public class DoctorControllerTest extends AbstractControllerTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void createPatientTest() {
        addEntityCheck();
    }

    @Test
    public void getPatientTest() {
        getEntityTest(DoctorDto.class);
    }

    @Test
    public void removePatientTest() {
        removeEntityTest();
    }

    @Test
    public void findPatientsTest() {
        findEntitiesTest(new TypeReference<List<DoctorDto>>() {
        });
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
        return "/doctor";
    }
}