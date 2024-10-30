package by.gp.clinic.mapper;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.mock.ShiftTimingMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShiftTimingDboDtoMapperTest extends AbstractSpringMvcTest {

    @Autowired
    private ShiftTimingDboDtoMapper mapper;

    @Test
    public void mapToDboTest() {
        final var dto = ShiftTimingMock.getShiftTimingDtoMock();
        final var dbo = mapper.mapToDbo(dto);

        assertEquals(dto.getId(), dbo.getId());
        assertEquals(dto.getShiftOrder(), dbo.getShiftOrder());
        assertEquals(dto.getStartTime(), dbo.getStartTime());
        assertEquals(dto.getEndTime(), dbo.getEndTime());
    }

    @Test
    public void mapToDtoTest() {
        final var dbo = ShiftTimingMock.getShiftTimingDboMock();
        final var dto = mapper.mapToDto(dbo);

        assertEquals(dbo.getId(), dto.getId());
        assertEquals(dbo.getShiftOrder(), dto.getShiftOrder());
        assertEquals(dbo.getStartTime(), dto.getStartTime());
        assertEquals(dbo.getEndTime(), dto.getEndTime());
    }
}
