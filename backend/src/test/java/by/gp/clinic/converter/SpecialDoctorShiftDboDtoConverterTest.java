package by.gp.clinic.converter;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.dbo.SpecialDoctorShiftDbo;
import by.gp.clinic.dto.SpecialDoctorShiftDto;
import by.gp.clinic.mock.SpecialDoctorShiftMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialDoctorShiftDboDtoConverterTest extends AbstractSpringMvcTest {

    @Autowired
    private SpecialDoctorShiftDboDtoConverter converter;

    @Test
    public void convertToDboWithDoctorIdTest() {
        covertToDbo(SpecialDoctorShiftMock.getSpecialDoctorShiftDoctorIdDtoMock());
    }

    @Test
    public void convertToDboWithSpecialityTest() {
        covertToDbo(SpecialDoctorShiftMock.getSpecialDoctorShiftSpecialityDtoMock());
    }

    @Test
    public void convertToDtoWithDoctorIdTest() {
        convertToDbo(SpecialDoctorShiftMock.getSpecialDoctorShiftDoctorIdDboMock());
    }

    @Test
    public void convertToDtoWithSpecialityTest() {
        convertToDbo(SpecialDoctorShiftMock.getSpecialDoctorShiftSpecialityDboMock());
    }

    private void covertToDbo(final SpecialDoctorShiftDto dto) {
        final var dbo = converter.convertToDbo(dto);

        assertEquals(dto.getId(), dbo.getId());
        assertEquals(dto.getDay(), dbo.getDay());
        if (dbo.getDoctor() != null) {
            assertEquals(dto.getDoctorId(), dbo.getDoctor().getId());
        }
    }

    private void convertToDbo(final SpecialDoctorShiftDbo dbo) {
        final var dto = converter.convertToDto(dbo);

        assertEquals(dbo.getId(), dto.getId());
        assertEquals(dbo.getDay(), dto.getDay());
        if (dbo.getDoctor() != null) {
            assertEquals(dbo.getDoctor().getId(), dto.getDoctorId());
        }
    }
}
