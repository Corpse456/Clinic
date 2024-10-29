package by.gp.clinic.mapper;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.mock.DoctorShiftMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoctorShiftDboDtoMapperTest extends AbstractSpringMvcTest {

    @Autowired
    private DoctorShiftDboDtoMapper mapper;

    @Test
    public void mapToDboTest() {
        final var dto = DoctorShiftMock.getDoctorShiftDtoMock();
        final var dbo = mapper.mapToDbo(dto);

        assertEquals(dto.getId(), dbo.getId());
        assertEquals(dto.getDate(), dbo.getDate());
        assertEquals(dto.getDoctorId(), dbo.getDoctor().getId());
        assertEquals(dto.getShiftTiming().getId(), dbo.getShiftTiming().getId());
    }

    @Test
    public void mapToDtoTest() {
        final var dbo = DoctorShiftMock.getDoctorShiftDboMock();
        final var dto = mapper.mapToDto(dbo);

        assertEquals(dbo.getId(), dto.getId());
        assertEquals(dbo.getDate(), dto.getDate());
        assertEquals(dbo.getDoctor().getId(), dto.getDoctorId());
        assertEquals(dbo.getShiftTiming().getId(), dto.getShiftTiming().getId());
    }
}
