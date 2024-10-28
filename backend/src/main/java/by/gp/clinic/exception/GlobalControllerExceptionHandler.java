package by.gp.clinic.exception;

import by.gp.clinic.dto.ConstraintViolationDto;
import by.gp.clinic.dto.HttpExceptionAnswerDto;
import by.gp.clinic.factory.ConstraintViolationMessageFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalControllerExceptionHandler {

    private final ConstraintViolationMessageFactory messageFactory;

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<HttpExceptionAnswerDto> handleBusinessLogicException(final BusinessLogicException e) {
        return generateResponseWithStatus(e, e.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ConstraintViolationDto>> handleConstraintViolationException(final ConstraintViolationException e) {
        return ResponseEntity.badRequest().body(messageFactory.build(e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ConstraintViolationDto>> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(messageFactory.build(e));
    }

    private ResponseEntity<HttpExceptionAnswerDto> generateResponseWithStatus(final Exception e,
                                                                              final HttpStatus status) {
        return ResponseEntity.status(status).body(new HttpExceptionAnswerDto(e.getMessage()));
    }
}
