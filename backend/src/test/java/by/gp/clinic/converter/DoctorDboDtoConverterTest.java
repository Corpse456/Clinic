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
        convertToDbo(DoctorMock.getDoctorDtoMock());
    }

    @Test
    public void convertToDtoTest() {
        convertToDto(DoctorMock.getDoctorDboMock());
    }

    @Test
    public void convertListToDboTest() {
        DoctorMock.getListDoctorDtoMock().forEach(this::convertToDbo);
    }

    @Test
    public void convertListToDtoTest() {
        DoctorMock.getListDoctorDboMock().forEach(this::convertToDto);
    }

    private void convertListToDbo(final DoctorDto dto) {
        converter.convertToDbo(DoctorMock.getListDoctorDboMock());
    }

    private void convertToDbo(final DoctorDto dto) {
        final DoctorDbo dbo = converter.convertToDbo(dto);
        checkConverting(dto, dbo);
    }

    private void convertToDto(final DoctorDbo dbo) {
        final DoctorDto dto = converter.convertToDto(dbo);
        checkConverting(dto, dbo);
    }

    private void checkConverting(final DoctorDto dto, final DoctorDbo dbo) {
        assertEquals(dto.getId(), dbo.getId());
        assertEquals(dto.getSpeciality(), dbo.getSpeciality());
        assertEquals(dto.getBirthDate(), dbo.getBirthDate());
        assertEquals(dto.getGender(), dbo.getGender());
        assertEquals(dto.getName(), dbo.getName());
        assertEquals(dto.getLastName(), dbo.getLastName());
    }
}
