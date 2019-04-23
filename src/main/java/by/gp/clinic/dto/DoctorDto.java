package by.gp.clinic.dto;

import by.gp.clinic.enums.Specialty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DoctorDto extends ManDto {

    private Specialty specialty;
}
