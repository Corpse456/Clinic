package by.gp.clinic.mapper;

import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.ShiftTimingDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShiftTimingDboDtoMapper extends AbstractDboDtoMapper<ShiftTimingDbo, ShiftTimingDto> {
}
