package by.gp.clinic.dto;

import by.gp.clinic.annotation.ShiftTiming;
import by.gp.clinic.annotation.SpecialDoctorShift;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.lang.Nullable;

import jakarta.validation.constraints.NotNull;
import java.time.DayOfWeek;

@Data
@EqualsAndHashCode(callSuper = true)
@SpecialDoctorShift
public class SpecialDoctorShiftDto extends AbstractDto {

    @Nullable
    private Long doctorId;

    @Nullable
    private Long specialityId;

    @NotNull
    private DayOfWeek weekDay;

    @NotNull
    @ShiftTiming
    private ShiftTimingDto shiftTiming;
}

