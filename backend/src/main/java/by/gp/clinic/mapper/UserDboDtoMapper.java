package by.gp.clinic.mapper;

import by.gp.clinic.dbo.UserDbo;
import by.gp.clinic.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDboDtoMapper extends AbstractDboDtoMapper<UserDbo, UserDto> {

}
