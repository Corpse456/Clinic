package by.gp.clinic.converter;

import by.gp.clinic.dbo.SpecialityDbo;
import by.gp.clinic.dto.SpecialityDto;
import org.springframework.stereotype.Service;

@Service
public class SpecialityDboDtoConverter extends AbstractDboDtoConverter<SpecialityDbo, SpecialityDto> {

    @Override
    protected SpecialityDto constructDto() {
        return new SpecialityDto();
    }

    @Override
    protected SpecialityDbo constructDbo() {
        return new SpecialityDbo();
    }
}
