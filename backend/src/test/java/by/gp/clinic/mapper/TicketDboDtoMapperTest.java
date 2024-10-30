package by.gp.clinic.mapper;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.mock.TicketMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketDboDtoMapperTest extends AbstractSpringMvcTest {

    @Autowired
    private TicketDboDtoMapper mapper;

    @Test
    public void mapToDboTest() {
        final var dto = TicketMock.getTicketDtoMock();
        final var dbo = mapper.mapToDbo(dto);

        assertEquals(dto.getId(), dbo.getId());
        assertEquals(dto.getNumber(), dbo.getNumber());
        assertEquals(dto.getDoctorId(), dbo.getDoctor().getId());
        assertEquals(dto.getPatientId(), dbo.getPatient().getId());
        assertEquals(dto.getDateTime(), dbo.getDateTime());
    }

    @Test
    public void mapToDtoTest() {
        final var dbo = TicketMock.getTicketDboMock();
        final var dto = mapper.mapToDto(dbo);

        assertEquals(dbo.getId(), dto.getId());
        assertEquals(dbo.getNumber(), dto.getNumber());
        assertEquals(dbo.getDoctor().getId(), dto.getDoctorId());
        assertEquals(dbo.getPatient().getId(), dto.getPatientId());
        assertEquals(dbo.getDateTime(), dto.getDateTime());
    }
}
