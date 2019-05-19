package by.gp.clinic.validation;

import by.gp.clinic.annotation.DateQuarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DateQuarterValidator implements ConstraintValidator<DateQuarter, LocalDateTime> {

    private DateQuarter constraintAnnotation;
    private int onePatientTime;

    @Override
    public void initialize(final DateQuarter constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Autowired
    public DateQuarterValidator(@Value("${clinic.one.patient.minutes}") final int onePatientTime) {
        this.onePatientTime = onePatientTime;
    }

    @Override
    public boolean isValid(final LocalDateTime value, final ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (value == null || !isMultiple(value)) {
            context.buildConstraintViolationWithTemplate(constraintAnnotation.message()).addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean isMultiple(final LocalDateTime value) {
        return value.getMinute() % onePatientTime == 0 && value.getSecond() == 0;
    }
}
