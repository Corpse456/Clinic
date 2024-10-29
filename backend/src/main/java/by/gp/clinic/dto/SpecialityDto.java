package by.gp.clinic.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpecialityDto extends AbstractDto {

    private String name;
}
