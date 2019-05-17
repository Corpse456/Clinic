package by.gp.clinic.dto;

import by.gp.clinic.enumerated.Gender;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ManDto extends AbstractDto {

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    private Gender gender;
/*
    @JsonDeserialize(using = ClinicDateDeserializer.class)
    @JsonSerialize(using = ClinicDateSerializer.class)*/
    private LocalDate birthDate;
}
