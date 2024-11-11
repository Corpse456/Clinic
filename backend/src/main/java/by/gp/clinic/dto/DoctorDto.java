package by.gp.clinic.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DoctorDto extends ManDto {

    @NotNull
    private Long specialityId;

    private String specialIdentifier;
}
