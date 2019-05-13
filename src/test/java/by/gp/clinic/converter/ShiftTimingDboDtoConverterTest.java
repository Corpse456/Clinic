package by.gp.clinic.converter;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.ShiftTimingDto;
import by.gp.clinic.mock.ShiftTimingMock;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class ShiftTimingDboDtoConverterTest extends AbstractSpringMvcTest {

    @Autowired
    private ShiftTimingDboDtoConverter converter;

    @Test
    public void convertToDboTest() {
        final ShiftTimingDto dto = ShiftTimingMock.getShiftTimingDtoMock();
        final ShiftTimingDbo dbo = converter.convertToDbo(dto);

        assertEquals(dto.getId(), dbo.getId());
        assertEquals(dto.getShiftOrder(), dbo.getShiftOrder());
        assertEquals(dto.getStartTime(), dbo.getStartTime());
        assertEquals(dto.getEndTime(), dbo.getEndTime());
    }

    @Test
    public void convertToDtoTest() {
        final ShiftTimingDbo dbo = ShiftTimingMock.getShiftTimingDboMock();
        final ShiftTimingDto dto = converter.convertToDto(dbo);

        assertEquals(dbo.getId(), dto.getId());
        assertEquals(dbo.getShiftOrder(), dto.getShiftOrder());
        assertEquals(dbo.getStartTime(), dto.getStartTime());
        assertEquals(dbo.getEndTime(), dto.getEndTime());
    }
}