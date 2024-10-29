package by.gp.clinic.dto;

import by.gp.clinic.annotation.Password;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
