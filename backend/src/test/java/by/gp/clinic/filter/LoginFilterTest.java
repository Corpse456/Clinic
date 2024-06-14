package by.gp.clinic.filter;

import by.gp.clinic.service.TokenAuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LoginFilterTest {

    @InjectMocks
    private LoginFilter loginFilter;
    @Mock
    private TokenAuthenticationService tokenAuthenticationService;

    @Test
    public void successfulAuthentication() {
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = new MockFilterChain();
        var authentication = Mockito.mock(Authentication.class);

        doNothing().when(tokenAuthenticationService).addAuthentication(any(), any(), any());

        loginFilter.successfulAuthentication(request, response, chain, authentication);
        verify(tokenAuthenticationService, times(1)).addAuthentication(any(), any(), any());
    }
}
