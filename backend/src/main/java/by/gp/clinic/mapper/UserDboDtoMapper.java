package by.gp.clinic.mapper;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.PatientDbo;
import by.gp.clinic.dbo.UserDbo;
import by.gp.clinic.dto.UserDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserDboDtoMapper extends AbstractDboDtoMapper<UserDbo, UserDto> {

    @Override
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patient", ignore = true)
    UserDbo mapToDbo(UserDto userDto);

    @Override
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "patientId", ignore = true)
    @Mapping(target = "doctorId", ignore = true)
    UserDto mapToDto(UserDbo userDbo);

    @AfterMapping
    default void fillDtoFields(@MappingTarget final UserDto targetDto, final UserDbo sourceDbo) {
        if (sourceDbo.getDoctor() != null) {
            targetDto.setDoctorId(sourceDbo.getDoctor().getId());
            targetDto.setName(sourceDbo.getDoctor().getName());
            targetDto.setLastName(sourceDbo.getDoctor().getLastName());
        }
        if (sourceDbo.getPatient() != null) {
            targetDto.setPatientId(sourceDbo.getPatient().getId());
            targetDto.setName(sourceDbo.getPatient().getName());
            targetDto.setLastName(sourceDbo.getPatient().getLastName());
        }
    }

    @AfterMapping
    default void fillDboFields(@MappingTarget final UserDbo targetDbo, final UserDto sourceDto) {
        if (sourceDto.getDoctorId() != null) {
            targetDbo.setDoctor(DoctorDbo.buildEmptyWithId(sourceDto.getDoctorId()));
        }
        if (sourceDto.getPatientId() != null) {
            targetDbo.setPatient(PatientDbo.buildEmptyWithId(sourceDto.getPatientId()));
        }
    }
}
