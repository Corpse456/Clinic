package by.gp.clinic.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClinicDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    public static final String DATE_TIME_PATTERN = "yyyy.MM.dd HH:mm:ss";

    @Override
    public void serialize(final LocalDateTime value, final JsonGenerator gen, final SerializerProvider serializers)
        throws IOException {
        final var formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        gen.writeString(formatter.format(value));
    }
}
