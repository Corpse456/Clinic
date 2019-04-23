package by.gp.clinic.dto;

import by.gp.clinic.enums.Gender;
import by.gp.clinic.serializer.ClinicDateDeserializer;
import by.gp.clinic.serializer.ClinicDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ManDto extends AbstractEntityDto {

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    private Gender gender;

    @JsonDeserialize(using = ClinicDateDeserializer.class)
    @JsonSerialize(using = ClinicDateSerializer.class)
    private LocalDate birthDate;
}
