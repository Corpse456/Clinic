package by.gp.clinic.converter;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.dbo.DoctorShiftDbo;
import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.mock.DoctorShiftMock;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class DoctorShiftDboDtoConverterTest extends AbstractSpringMvcTest {

    @Autowired
    private DoctorShiftDboDtoConverter converter;

    @Test
    public void convertToDboTest() {
        final DoctorShiftDto dto = DoctorShiftMock.getDoctorShiftDtoMock();
        final DoctorShiftDbo dbo = converter.convertToDbo(dto);

        assertEquals(dto.getId(), dbo.getId());
        assertEquals(dto.getDate(), dbo.getDate());
        assertEquals(dto.getDoctorId(), dbo.getDoctor().getId());
        assertEquals(dto.getShiftTiming().getId(), dbo.getShiftTiming().getId());
    }

    @Test
    public void convertToDtoTest() {
        final DoctorShiftDbo dbo = DoctorShiftMock.getDoctorShiftDboMock();
        final DoctorShiftDto dto = converter.convertToDto(dbo);

        assertEquals(dbo.getId(), dto.getId());
        assertEquals(dbo.getDate(), dto.getDate());
        assertEquals(dbo.getDoctor().getId(), dto.getDoctorId());
        assertEquals(dbo.getShiftTiming().getId(), dto.getShiftTiming().getId());
    }
}