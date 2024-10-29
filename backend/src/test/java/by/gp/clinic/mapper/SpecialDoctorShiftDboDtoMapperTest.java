package by.gp.clinic.mapper;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.dbo.SpecialDoctorShiftDbo;
import by.gp.clinic.dto.SpecialDoctorShiftDto;
import by.gp.clinic.mock.SpecialDoctorShiftMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialDoctorShiftDboDtoMapperTest extends AbstractSpringMvcTest {

    @Autowired
    private SpecialDoctorShiftDboDtoMapper mapper;

    @Test
    public void mapToDboWithDoctorIdTest() {
        covertToDbo(SpecialDoctorShiftMock.getSpecialDoctorShiftDoctorIdDtoMock());
    }

    @Test
    public void mapToDboWithSpecialityTest() {
        covertToDbo(SpecialDoctorShiftMock.getSpecialDoctorShiftSpecialityDtoMock());
    }

    @Test
    public void mapToDtoWithDoctorIdTest() {
        mapToDbo(SpecialDoctorShiftMock.getSpecialDoctorShiftDoctorIdDboMock());
    }

    @Test
    public void mapToDtoWithSpecialityTest() {
        mapToDbo(SpecialDoctorShiftMock.getSpecialDoctorShiftSpecialityDboMock());
    }

    private void covertToDbo(final SpecialDoctorShiftDto dto) {
        final var dbo = mapper.mapToDbo(dto);

        assertEquals(dto.getId(), dbo.getId());
        assertEquals(dto.getWeekDay(), dbo.getWeekDay());
        if (dbo.getDoctor() != null) {
            assertEquals(dto.getDoctorId(), dbo.getDoctor().getId());
        }
    }

    private void mapToDbo(final SpecialDoctorShiftDbo dbo) {
        final var dto = mapper.mapToDto(dbo);

        assertEquals(dbo.getId(), dto.getId());
        assertEquals(dbo.getWeekDay(), dto.getWeekDay());
        if (dbo.getDoctor() != null) {
            assertEquals(dbo.getDoctor().getId(), dto.getDoctorId());
        }
    }
}
