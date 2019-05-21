package by.gp.clinic.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class CredentialsDto {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
