package by.gp.clinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @JsonIgnore
    @Schema(hidden = true)
    private String specialIdentifier;
}
