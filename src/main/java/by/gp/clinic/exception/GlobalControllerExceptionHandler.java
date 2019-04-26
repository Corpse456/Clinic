package by.gp.clinic.exception;

import by.gp.clinic.dto.HttpExceptionAnswerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<HttpExceptionAnswerDto> handleMembershipConditionNotFound(final BusinessLogicException e) {
        return generateResponseWithStatus(e, e.getStatus());
    }

    private ResponseEntity<HttpExceptionAnswerDto> generateResponseWithStatus(final Exception e,
                                                                              final HttpStatus status) {
        return ResponseEntity.status(status).body(new HttpExceptionAnswerDto(e.getMessage()));
    }
}
