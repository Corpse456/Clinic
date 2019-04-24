package by.gp.clinic.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class ClinicDateSerializer extends JsonSerializer<LocalDate> {

    public static final String DATE_PATTERN = "yyyy.MM.dd";

    @Override
    public void serialize(final LocalDate value, final JsonGenerator gen, final SerializerProvider serializers)
        throws IOException {
        final Date date = Date.valueOf(value);
        final String formattedDate = new SimpleDateFormat(DATE_PATTERN).format(date);
        gen.writeString(formattedDate);
    }
}
