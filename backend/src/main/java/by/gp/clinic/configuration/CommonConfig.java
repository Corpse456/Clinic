package by.gp.clinic.configuration;

import by.gp.clinic.serializer.ClinicDateDeserializer;
import by.gp.clinic.serializer.ClinicDateSerializer;
import by.gp.clinic.serializer.ClinicDateTimeDeserializer;
import by.gp.clinic.serializer.ClinicDateTimeSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@EnableScheduling
@PropertySources({
    @PropertySource("classpath:/properties/clinic.properties")
})
public class CommonConfig {

    @Bean
    public ResourceBundleMessageSource messageSourceResourceBundle() {
         final ResourceBundleMessageSource messageSourceResourceBundle = new ResourceBundleMessageSource();
        messageSourceResourceBundle.setBasenames("properties/enum");
        return messageSourceResourceBundle;
    }

    @Bean
    @Primary
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.simpleDateFormat(ClinicDateTimeSerializer.DATE_TIME_PATTERN);
        final SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDate.class, new ClinicDateSerializer());
        simpleModule.addSerializer(LocalDateTime.class, new ClinicDateTimeSerializer());
        simpleModule.addDeserializer(LocalDate.class, new ClinicDateDeserializer());
        simpleModule.addDeserializer(LocalDateTime.class, new ClinicDateTimeDeserializer());
        return builder.modulesToInstall(new JavaTimeModule());
    }
}
