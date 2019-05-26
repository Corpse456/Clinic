package by.gp.clinic.search;

import by.gp.clinic.serializer.ClinicDateTimeDeserializer;
import by.gp.clinic.serializer.ClinicDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class TicketSearchRequest extends PageableSearchRequest {

    private Long patientId;

    private Long doctorId;

    @JsonDeserialize(using = ClinicDateTimeDeserializer.class)
    @JsonSerialize(using = ClinicDateTimeSerializer.class)
    private LocalDateTime from;

    @JsonDeserialize(using = ClinicDateTimeDeserializer.class)
    @JsonSerialize(using = ClinicDateTimeSerializer.class)
    private LocalDateTime to;
}
