package by.gp.clinic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AbstractEntityDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
}
