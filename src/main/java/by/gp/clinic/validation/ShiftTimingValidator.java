package by.gp.clinic.validation;

import by.gp.clinic.annotation.ShiftTming;
import by.gp.clinic.dto.ShiftTimingDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static by.gp.clinic.util.ValidationUtils.addMessageToContext;
import static by.gp.clinic.util.ValidationUtils.checkNotNullFields;

public class ShiftTimingValidator implements ConstraintValidator<ShiftTming, ShiftTimingDto> {
    @Override
    public boolean isValid(final ShiftTimingDto value, final ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        boolean valid = checkNotNullFields(value, context);

        if (value.getStartTime() != null && value.getEndTime() != null
            && value.getEndTime().isBefore(value.getStartTime())) {
            addMessageToContext("startTime", "Start time must be before end time", context);
            valid = false;
        }

        return valid;
    }
}
