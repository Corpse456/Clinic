package by.gp.clinic.dto;

import by.gp.clinic.enumerated.UserRole;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto extends AbstractDto {

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private UserRole role;
}
