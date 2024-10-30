package by.gp.clinic.mapper;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.SpecialityDbo;
import by.gp.clinic.dto.DoctorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DoctorDboDtoMapper extends AbstractDboDtoMapper<DoctorDbo, DoctorDto> {

    @Override
    @Mapping(target = "speciality", source = "specialityId", qualifiedByName = "buildEmptySpeciality")
    DoctorDbo mapToDbo(DoctorDto doctorDto);

    @Override
    @Mapping(target = "specialityId", expression = "java(doctorDbo.getSpeciality().getId())")
    DoctorDto mapToDto(DoctorDbo doctorDbo);

    @Named("buildEmptySpeciality")
    static SpecialityDbo buildEmptySpeciality(Long specialityId) {
        return SpecialityDbo.buildEmptyWithId(specialityId);
    }
}
