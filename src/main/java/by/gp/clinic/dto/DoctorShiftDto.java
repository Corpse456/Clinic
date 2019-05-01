package by.gp.clinic.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DoctorShiftDto extends AbstractDto {

    private DoctorDto doctor;

    private ShiftTimingDto shiftTiming;

    private LocalDate date;
}
