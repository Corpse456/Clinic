package by.gp.clinic.validation;

import by.gp.clinic.annotation.ShiftTming;
import by.gp.clinic.dto.ShiftTimingDto;

import javax.validation.ConstraintValidatorContext;

import static by.gp.clinic.util.ValidationUtils.addMessageToContext;

public class ShiftTimingValidator extends AbstractValidator<ShiftTming, ShiftTimingDto> {

    @Override
    protected boolean checkValid(final ShiftTimingDto value, final ConstraintValidatorContext context) {
        boolean valid = true;

        if (value.getEndTime().isBefore(value.getStartTime())) {
            addMessageToContext("startTime", "Start time must be before end time", context);
            valid = false;
        }

        return valid;
    }

    @Override
    protected String getClassName() {
        return "Shift Timing";
    }
}
