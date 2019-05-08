package by.gp.clinic.dto;

import by.gp.clinic.serializer.ClinicDateTimeDeserializer;
import by.gp.clinic.serializer.ClinicDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TicketDto extends AbstractDto {

    private Long patientId;

    private Long doctorId;

    @JsonDeserialize(using = ClinicDateTimeDeserializer.class)
    @JsonSerialize(using = ClinicDateTimeSerializer.class)
    private LocalDateTime dateTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int number;
}
