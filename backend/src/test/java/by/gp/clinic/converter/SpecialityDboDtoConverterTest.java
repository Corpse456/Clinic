package by.gp.clinic.converter;

import by.gp.clinic.AbstractSpringMvcTest;
import by.gp.clinic.dbo.SpecialityDbo;
import by.gp.clinic.dto.SpecialityDto;
import by.gp.clinic.mock.SpecialityMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialityDboDtoConverterTest extends AbstractSpringMvcTest {

    @Autowired
    private SpecialityDboDtoConverter converter;

    @Test
    public void convertToDboTest() {
        convertToDbo(SpecialityMock.getSpecialityDtoMock());
    }

    @Test
    public void convertToDtoTest() {
        convertToDto(SpecialityMock.getSpecialityDboMock());
    }

    private void convertToDbo(final SpecialityDto dto) {
        final var dbo = converter.convertToDbo(dto);
        checkConverting(dto, dbo);
    }

    private void convertToDto(final SpecialityDbo dbo) {
        final var dto = converter.convertToDto(dbo);
        checkConverting(dto, dbo);
    }

    private void checkConverting(final SpecialityDto dto, final SpecialityDbo dbo) {
        assertEquals(dto.getId(), dbo.getId());
        assertEquals(dto.getName(), dbo.getName());
    }
}
