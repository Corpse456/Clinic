package by.gp.clinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AbstractEntityDto {

    @JsonIgnore
    private Long id;
}
