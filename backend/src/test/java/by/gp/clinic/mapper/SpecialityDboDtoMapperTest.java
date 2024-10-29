package by.gp.clinic.mapper;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.dbo.SpecialityDbo;
import by.gp.clinic.dto.SpecialityDto;
import by.gp.clinic.mock.SpecialityMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialityDboDtoMapperTest extends AbstractSpringMvcTest {

    @Autowired
    private SpecialityDboDtoMapper mapper;

    @Test
    public void mapToDboTest() {
        mapToDbo(SpecialityMock.getSpecialityDtoMock());
    }

    @Test
    public void mapToDtoTest() {
        mapToDto(SpecialityMock.getSpecialityDboMock());
    }

    private void mapToDbo(final SpecialityDto dto) {
        final var dbo = mapper.mapToDbo(dto);
        checkConverting(dto, dbo);
    }

    private void mapToDto(final SpecialityDbo dbo) {
        final var dto = mapper.mapToDto(dbo);
        checkConverting(dto, dbo);
    }

    private void checkConverting(final SpecialityDto dto, final SpecialityDbo dbo) {
        assertEquals(dto.getId(), dbo.getId());
        assertEquals(dto.getName(), dbo.getName());
    }
}
