package by.gp.clinic.mapper;

import by.gp.clinic.dbo.SpecialDoctorShiftDbo;
import by.gp.clinic.dto.SpecialDoctorShiftDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecialDoctorShiftDboDtoMapper
    extends AbstractDboDtoMapper<SpecialDoctorShiftDbo, SpecialDoctorShiftDto> {
}
