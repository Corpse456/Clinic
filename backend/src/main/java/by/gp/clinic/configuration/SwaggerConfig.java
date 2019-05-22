package by.gp.clinic.configuration;

import by.gp.clinic.service.TokenAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.util.List;

import static by.gp.clinic.service.TokenAuthenticationService.HEADER_STRING;
import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfig {

    private final Jackson2ObjectMapperBuilder objectMapperBuilder;
    private final TokenAuthenticationService authenticationService;
    private final DataSource dataSource;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .securitySchemes(newArrayList(apiKey()))
            .securityContexts(newArrayList(securityContext()))
            .enableUrlTemplating(true)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Clinic APIs")
            .description("APIs for work with clinic server")
            .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.ant("/**"))
            .build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope[] authorizationScopes = {new AuthorizationScope("global", "accessEverything")};
        return newArrayList(new SecurityReference("token", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("token", HEADER_STRING, "header");
    }
}
