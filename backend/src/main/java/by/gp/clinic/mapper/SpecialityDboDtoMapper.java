package by.gp.clinic.mapper;

import by.gp.clinic.dbo.SpecialityDbo;
import by.gp.clinic.dto.SpecialityDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecialityDboDtoMapper extends AbstractDboDtoMapper<SpecialityDbo, SpecialityDto> {
}
