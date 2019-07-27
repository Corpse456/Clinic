package by.gp.clinic.filter;

import by.gp.clinic.service.TokenAuthenticationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationFilterTest {

    @InjectMocks
    private AuthenticationFilter authenticationFilter;
    @Mock
    private TokenAuthenticationService tokenAuthenticationService;

    @Test
    public void doFilter() throws IOException, ServletException {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        final MockHttpServletResponse response = new MockHttpServletResponse();
        final FilterChain chain = Mockito.mock(FilterChain.class);
        final Authentication authentication = Mockito.mock(Authentication.class);

        doReturn(authentication).when(tokenAuthenticationService).getAuthentication(request);

        authenticationFilter.doFilter(request, response, chain);

        assertEquals(authentication, SecurityContextHolder.getContext().getAuthentication());
        verify(chain, times(1)).doFilter(request, response);
    }
}