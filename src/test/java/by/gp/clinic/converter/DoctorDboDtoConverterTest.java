package by.gp.clinic.converter;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.mock.DoctorMock;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class DoctorDboDtoConverterTest extends AbstractSpringMvcTest {

    @Autowired
    private DoctorDboDtoConverter converter;

    @Test
    public void convertToDboTest() {
        final DoctorDto doctorDto = DoctorMock.getDoctorDtoMock();
        final DoctorDbo doctorDbo = converter.convertToDbo(doctorDto);

        assertEquals(doctorDto.getId(), doctorDbo.getId());
        assertEquals(doctorDto.getSpeciality(), doctorDbo.getSpeciality());
        assertEquals(doctorDto.getBirthDate(), doctorDbo.getBirthDate());
        assertEquals(doctorDto.getGender(), doctorDbo.getGender());
        assertEquals(doctorDto.getName(), doctorDbo.getName());
        assertEquals(doctorDto.getLastName(), doctorDbo.getLastName());
    }

    @Test
    public void convertToDtoTest() {
        final DoctorDbo doctorDbo = DoctorMock.getDoctorDboMock();
        final DoctorDto doctorDto = converter.convertToDto(doctorDbo);

        assertEquals(doctorDbo.getId(), doctorDto.getId());
        assertEquals(doctorDbo.getSpeciality(), doctorDto.getSpeciality());
        assertEquals(doctorDbo.getBirthDate(), doctorDto.getBirthDate());
        assertEquals(doctorDbo.getGender(), doctorDto.getGender());
        assertEquals(doctorDbo.getName(), doctorDto.getName());
        assertEquals(doctorDbo.getLastName(), doctorDto.getLastName());
    }
}