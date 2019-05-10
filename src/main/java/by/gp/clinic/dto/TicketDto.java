package by.gp.clinic.dto;

import by.gp.clinic.annotation.DateInFuture;
import by.gp.clinic.annotation.DateQuarter;
import by.gp.clinic.serializer.ClinicDateTimeDeserializer;
import by.gp.clinic.serializer.ClinicDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TicketDto extends AbstractDto {

    @NotNull
    private Long patientId;

    @NotNull
    private Long doctorId;

    @JsonDeserialize(using = ClinicDateTimeDeserializer.class)
    @JsonSerialize(using = ClinicDateTimeSerializer.class)
    @NotNull
    @DateInFuture
    @DateQuarter
    private LocalDateTime dateTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer number;
}
