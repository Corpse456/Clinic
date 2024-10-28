package by.gp.clinic.dto;

import by.gp.clinic.enumerated.ShiftOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ShiftTimingDto extends AbstractDto {

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotNull
    private ShiftOrder shiftOrder;
}
