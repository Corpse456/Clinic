package by.gp.clinic.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DoctorDto extends ManDto {

    @NotNull
    private Long specialityId;
}
