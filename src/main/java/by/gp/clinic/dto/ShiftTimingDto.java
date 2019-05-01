package by.gp.clinic.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ShiftTimingDto extends AbstractDto {

    private LocalTime startTime;

    private LocalTime endTime;
}
