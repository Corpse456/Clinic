package by.gp.clinic.search;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorShiftSearchRequest extends PageableSearchRequest {

    private Long doctorId;

    private Long shiftTimingId;

    private LocalDate from;

    private LocalDate to;
}
