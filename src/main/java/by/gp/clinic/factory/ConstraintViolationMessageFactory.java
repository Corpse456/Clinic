package by.gp.clinic.factory;

import by.gp.clinic.dto.ConstraintViolationDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
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
        final String message = constraintViolation.getMessage();
        final Path propertyPath = constraintViolation.getPropertyPath();

        final ConstraintViolationDto violation = new ConstraintViolationDto();
        violation.setField(propertyPath.toString());
        violation.setMessage(message);

        return violation;
    }

    private ConstraintViolationDto build(final FieldError fieldError) {
        final ConstraintViolationDto constraintViolationDto = new ConstraintViolationDto();

        constraintViolationDto.setMessage(fieldError.getDefaultMessage());
        constraintViolationDto.setField(fieldError.getField());

        return constraintViolationDto;
    }
}
