package by.gp.clinic.dto;

import by.gp.clinic.annotation.Password;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CredentialsDto {

    @NotNull
    private String alias;

    @NotNull
    @Password
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    private String specialIdentifier;
}
