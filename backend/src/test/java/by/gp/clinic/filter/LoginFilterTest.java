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

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
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
        Authentication authentication = Mockito.mock(Authentication.class);

        doNothing().when(tokenAuthenticationService).addAuthentication(any(), any(), any());

        loginFilter.successfulAuthentication(request, response, chain, authentication);
        verify(tokenAuthenticationService, times(1)).addAuthentication(any(), any(), any());
    }
}
