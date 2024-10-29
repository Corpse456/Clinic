package by.gp.clinic.validation;

import by.gp.clinic.annotation.Password;
import jakarta.validation.ConstraintValidatorContext;

import static by.gp.clinic.util.ValidationUtils.addMessageToContext;

/**
 * Created by root on 10.09.18.
 */
public class PasswordValidator extends AbstractValidator<Password, String> {

    @Override
    protected boolean checkValid(final String value, final ConstraintValidatorContext context) {
        var valid = true;
        if (!value.matches(".*\\d.*")) {
            addMessageToContext(getClassName() + " must contains at least one digit", context);
            valid = false;
        }
        if (value.equals(value.toLowerCase())) {
            addMessageToContext(getClassName() + " must contains at least one upper case letter", context);
            valid = false;
        }
        if (value.equals(value.toUpperCase())) {
            addMessageToContext(getClassName() + " must contains at least one lower case letter", context);
            valid = false;
        }
        if (value.length() < 8) {
            addMessageToContext(getClassName() + " must be more or equals than 8 symbols", context);
            valid = false;
        }
        return valid;
    }

    @Override
    protected String getClassName() {
        return Password.class.getSimpleName();
    }
}
