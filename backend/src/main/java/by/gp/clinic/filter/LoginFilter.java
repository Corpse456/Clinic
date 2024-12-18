package by.gp.clinic.filter;

import by.gp.clinic.dto.CredentialsDto;
import by.gp.clinic.service.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private static final String LOGIN_URL = "/login";

    private final TokenAuthenticationService tokenAuthenticationService;

    private final Jackson2ObjectMapperBuilder objectMapperBuilder;

    private ObjectMapper objectMapper;

    public LoginFilter(final TokenAuthenticationService tokenAuthenticationService,
            final AuthenticationManager authenticationManager,
            final Jackson2ObjectMapperBuilder objectMapperBuilder) {
        super(new AntPathRequestMatcher(LOGIN_URL));
        this.objectMapperBuilder = objectMapperBuilder;
        this.tokenAuthenticationService = tokenAuthenticationService;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request,
            final HttpServletResponse response)
            throws AuthenticationException, IOException {
        final var credentials = getObjectMapper().readValue(request.getInputStream(), CredentialsDto.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getAlias(), credentials.getPassword(),
                        Collections.emptyList())
                                                      );
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain,
            final Authentication authResult) {
        final var alias = authResult.getName();
        final var roles = authResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        tokenAuthenticationService.addAuthentication(response, alias, roles);
    }

    private ObjectMapper getObjectMapper() {
        return objectMapper == null ? objectMapper = objectMapperBuilder.build() : objectMapper;
    }
}
