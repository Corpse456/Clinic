package by.gp.clinic.converter;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.mock.PatientMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientDboDtoConverterTest extends AbstractSpringMvcTest {

    @Autowired
    private PatientDboDtoConverter converter;

    @Test
    public void convertToDboTest() {
        final var dto = PatientMock.getPatientDtoMock();
        final var dbo = converter.convertToDbo(dto);

        assertEquals(dto.getId(), dbo.getId());
        assertEquals(dto.getBirthDate(), dbo.getBirthDate());
        assertEquals(dto.getGender(), dbo.getGender());
        assertEquals(dto.getName(), dbo.getName());
        assertEquals(dto.getLastName(), dbo.getLastName());
    }

    @Test
    public void convertToDtoTest() {
        final var dbo = PatientMock.getPatientDboMock();
        final var dto = converter.convertToDto(dbo);

        assertEquals(dbo.getId(), dto.getId());
        assertEquals(dbo.getBirthDate(), dto.getBirthDate());
        assertEquals(dbo.getGender(), dto.getGender());
        assertEquals(dbo.getName(), dto.getName());
        assertEquals(dbo.getLastName(), dto.getLastName());
    }
}
