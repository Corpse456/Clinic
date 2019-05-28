package by.gp.clinic.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CredentialsDto {

    @NotNull
    private String alias;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    private String specialIdentifier;
}
