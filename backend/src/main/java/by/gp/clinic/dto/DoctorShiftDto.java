package by.gp.clinic.dto;

import by.gp.clinic.annotation.DateInFuture;
import by.gp.clinic.annotation.ShiftTiming;
import by.gp.clinic.serializer.ClinicDateDeserializer;
import by.gp.clinic.serializer.ClinicDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class DoctorShiftDto extends AbstractDto {

    @NotNull
    private Long doctorId;

    @NotNull
    @ShiftTiming
    private ShiftTimingDto shiftTiming;

    @NotNull
    @DateInFuture
    @JsonDeserialize(using = ClinicDateDeserializer.class)
    @JsonSerialize(using = ClinicDateSerializer.class)
    private LocalDate date;
}
