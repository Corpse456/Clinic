package by.gp.clinic.configuration;

import by.gp.clinic.enumerated.UserRole;
import by.gp.clinic.filter.AuthenticationFilter;
import by.gp.clinic.filter.LoginFilter;
import by.gp.clinic.service.TokenAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final DataSource dataSource;

    private final TokenAuthenticationService tokenAuthenticationService;

    private final Jackson2ObjectMapperBuilder objectMapperBuilder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager)
            throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers("/development/**").permitAll()
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
                        .requestMatchers(HttpMethod.GET, "/webjars/springfox-swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                        .requestMatchers("/admin/**").hasAuthority(UserRole.ADMIN.name())
                        .requestMatchers("/**")
                        .hasAnyAuthority(UserRole.USER.name(), UserRole.DOCTOR.name(), UserRole.ADMIN.name())
                        .anyRequest().authenticated())
                .addFilterBefore(
                        new LoginFilter(tokenAuthenticationService, authenticationManager, objectMapperBuilder),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new AuthenticationFilter(tokenAuthenticationService),
                        UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        manager.setUsersByUsernameQuery("select alias, password, enabled from user where alias=?");
        manager.setAuthoritiesByUsernameQuery("select alias, role from user where alias=?");
        return manager;
    }
}
