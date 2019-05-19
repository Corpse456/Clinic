package by.gp.clinic.search;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class TicketSearchRequest extends PageableSearchRequest {

    private Long patientId;

    private Long doctorId;

    private LocalDateTime from;

    private LocalDateTime to;
}
