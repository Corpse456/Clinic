package by.gp.clinic.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertSame;

@RunWith(MockitoJUnitRunner.class)
public class CommonExceptionHandlerTest {

    @InjectMocks
    private CommonExceptionHandler commonExceptionHandler;

    @Test
    public void handleCommonException() {
        final MockitoException exception = new MockitoException("Exception");
        final ResponseEntity<String> response = commonExceptionHandler.handleCommonException(exception);

        assertSame(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}