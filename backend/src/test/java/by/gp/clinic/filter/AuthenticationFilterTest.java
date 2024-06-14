package by.gp.clinic.filter;

import by.gp.clinic.service.TokenAuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthenticationFilterTest {

    @InjectMocks
    private AuthenticationFilter authenticationFilter;
    @Mock
    private TokenAuthenticationService tokenAuthenticationService;

    @Test
    public void doFilter() throws IOException, ServletException {
        final var request = new MockHttpServletRequest();
        final var response = new MockHttpServletResponse();
        final FilterChain chain = Mockito.mock(FilterChain.class);
        final var authentication = Mockito.mock(Authentication.class);

        doReturn(authentication).when(tokenAuthenticationService).getAuthentication(request);

        authenticationFilter.doFilter(request, response, chain);

        assertEquals(authentication, SecurityContextHolder.getContext().getAuthentication());
        verify(chain, times(1)).doFilter(request, response);
    }
}
