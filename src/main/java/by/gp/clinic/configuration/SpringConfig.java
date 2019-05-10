package by.gp.clinic.configuration;

import by.gp.clinic.serializer.ClinicDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@PropertySources({
    @PropertySource("classpath:/properties/clinic.properties")
})
public class SpringConfig {

    @Bean
    @Primary
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true).simpleDateFormat(ClinicDateSerializer.DATE_PATTERN);
        return builder.modulesToInstall(new JavaTimeModule());
    }
}
