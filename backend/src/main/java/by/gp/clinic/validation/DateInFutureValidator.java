package by.gp.clinic.validation;

import by.gp.clinic.annotation.DateInFuture;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

/**
 * Created by root on 10.09.18.
 */
public class DateInFutureValidator implements ConstraintValidator<DateInFuture, Temporal> {

    private DateInFuture constraintAnnotation;

    @Override
    public void initialize(final DateInFuture constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(final Temporal value, final ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (value == null || !isBefore(value)) {
            context.buildConstraintViolationWithTemplate(constraintAnnotation.message()).addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean isBefore(final Temporal value) {
        if (value instanceof LocalDate) {
            return LocalDate.now().isBefore(LocalDate.from(value));
        } else if (value instanceof LocalDateTime) {
            return LocalDateTime.now().isBefore(LocalDateTime.from(value));
        }
        return false;
    }
}
