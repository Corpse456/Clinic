package by.gp.clinic.mapper;

import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.ShiftTimingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShiftTimingDboDtoMapper extends AbstractDboDtoMapper<ShiftTimingDbo, ShiftTimingDto> {

    @Override
    @Mapping(target = "oppositeShift", ignore = true)
    ShiftTimingDbo mapToDbo(ShiftTimingDto doctorDto);
}
