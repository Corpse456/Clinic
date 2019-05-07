package by.gp.clinic.dto;

import by.gp.clinic.serializer.ClinicDateTimeDeserializer;
import by.gp.clinic.serializer.ClinicDateTimeSerializer;
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

    private PatientDto patient;

    private DoctorDto doctor;

    @JsonDeserialize(using = ClinicDateTimeDeserializer.class)
    @JsonSerialize(using = ClinicDateTimeSerializer.class)
    private LocalDateTime dateTime;

    private int number;
}
