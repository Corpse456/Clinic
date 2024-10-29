package by.gp.clinic.factory;

import by.gp.clinic.dto.ConstraintViolationDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConstraintViolationMessageFactory {

    public List<ConstraintViolationDto> build(final ConstraintViolationException ex) {
        return ex.getConstraintViolations()
            .stream()
            .map(this::build)
            .collect(Collectors.toList());
    }

    public List<ConstraintViolationDto> build(final MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(this::build)
            .collect(Collectors.toList());
    }

    private ConstraintViolationDto build(final ConstraintViolation<?> constraintViolation) {
        final var message = constraintViolation.getMessage();
        final var propertyPath = constraintViolation.getPropertyPath();

        final var violation = new ConstraintViolationDto();
        violation.setField(propertyPath.toString());
        violation.setMessage(message);

        return violation;
    }

    private ConstraintViolationDto build(final FieldError fieldError) {
        final var constraintViolationDto = new ConstraintViolationDto();

        constraintViolationDto.setMessage(fieldError.getDefaultMessage());
        constraintViolationDto.setField(fieldError.getField());

        return constraintViolationDto;
    }
}
