package by.gp.clinic.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static by.gp.clinic.serializer.ClinicDateSerializer.DATE_PATTERN;

public class ClinicDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(final JsonParser p, final DeserializationContext context)
        throws IOException {
        return LocalDate.parse(p.getValueAsString(), DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}
