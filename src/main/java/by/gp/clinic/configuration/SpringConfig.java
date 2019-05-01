package by.gp.clinic.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@PropertySources({
    @PropertySource("classpath:/properties/clinic.properties")
})
public class SpringConfig {
}
