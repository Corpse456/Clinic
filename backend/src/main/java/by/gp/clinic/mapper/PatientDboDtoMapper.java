package by.gp.clinic.mapper;

import by.gp.clinic.dbo.PatientDbo;
import by.gp.clinic.dto.PatientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientDboDtoMapper extends AbstractDboDtoMapper<PatientDbo, PatientDto> {
}
