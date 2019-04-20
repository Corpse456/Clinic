package by.gp.clinic.dto;

import by.gp.clinic.enums.Specialty;
import lombok.Data;

@Data
public class DoctorDto extends ManDto {

    private Specialty specialty;
}
