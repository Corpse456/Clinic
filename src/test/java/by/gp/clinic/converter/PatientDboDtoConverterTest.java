package by.gp.clinic.converter;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.dbo.PatientDbo;
import by.gp.clinic.dto.PatientDto;
import by.gp.clinic.mock.PatientMock;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class PatientDboDtoConverterTest extends AbstractSpringMvcTest {

    @Autowired
    private PatientDboDtoConverter converter;

    @Test
    public void convertToDboTest() {
        final PatientDto patientDto = PatientMock.getPatientDtoMock();
        final PatientDbo patientDbo = converter.convertToDbo(patientDto);

        assertEquals(patientDto.getId(), patientDbo.getId());
        assertEquals(patientDto.getBirthDate(), patientDbo.getBirthDate());
        assertEquals(patientDto.getGender(), patientDbo.getGender());
        assertEquals(patientDto.getName(), patientDbo.getName());
        assertEquals(patientDto.getLastName(), patientDbo.getLastName());
    }

    @Test
    public void convertToDtoTest() {
        final PatientDbo patientDbo = PatientMock.getPatientDboMock();
        final PatientDto patientDto = converter.convertToDto(patientDbo);

        assertEquals(patientDbo.getId(), patientDto.getId());
        assertEquals(patientDbo.getBirthDate(), patientDto.getBirthDate());
        assertEquals(patientDbo.getGender(), patientDto.getGender());
        assertEquals(patientDbo.getName(), patientDto.getName());
        assertEquals(patientDbo.getLastName(), patientDto.getLastName());
    }
}