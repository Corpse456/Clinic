package by.gp.clinic.dto;

import by.gp.clinic.enumerated.Gender;
import by.gp.clinic.serializer.ClinicDateDeserializer;
import by.gp.clinic.serializer.ClinicDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class ManDto extends AbstractDto {

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    private Gender gender;

    @JsonDeserialize(using = ClinicDateDeserializer.class)
    @JsonSerialize(using = ClinicDateSerializer.class)
    private LocalDate birthDate;
}
