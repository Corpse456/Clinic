package by.gp.clinic.dto;

import by.gp.clinic.serializer.ClinicDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class HttpExceptionAnswerDto implements Serializable {

    @JsonSerialize(using = ClinicDateTimeSerializer.class)
    private LocalDateTime time;

    private String message;

    public HttpExceptionAnswerDto(final String message) {
        this.message = message;
        time = LocalDateTime.now();
    }
}
