package by.gp.clinic.dto;

import by.gp.clinic.annotation.ShiftTming;
import by.gp.clinic.annotation.SpecialDoctorShift;
import by.gp.clinic.enums.Speciality;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;

@Data
@EqualsAndHashCode(callSuper = true)
@SpecialDoctorShift
public class SpecialDoctorShiftDto extends AbstractDto {

    private Long doctorId;

    private Speciality speciality;

    @NotNull
    private DayOfWeek day;

    @NotNull
    @ShiftTming
    private ShiftTimingDto shiftTiming;
}

