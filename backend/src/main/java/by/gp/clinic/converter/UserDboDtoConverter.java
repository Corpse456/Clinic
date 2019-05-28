package by.gp.clinic.converter;

import by.gp.clinic.dbo.UserDbo;
import by.gp.clinic.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserDboDtoConverter extends AbstractDboDtoConverter<UserDbo, UserDto> {

    @Override
    protected UserDto constructDto() {
        return new UserDto();
    }

    @Override
    protected UserDbo constructDbo() {
        return new UserDbo();
    }

    @Override
    protected void convertComplexFieldsForDto(final UserDbo sourceDbo, final UserDto targetDto) {
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

    @Override
    protected void convertComplexFieldsForDbo(final UserDto sourceDto, final UserDbo targetDbo) {
        super.convertComplexFieldsForDbo(sourceDto, targetDbo);
    }
}
