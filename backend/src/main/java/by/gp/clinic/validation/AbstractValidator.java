package by.gp.clinic.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

import static by.gp.clinic.util.ValidationUtils.addMessageToContext;
import static by.gp.clinic.util.ValidationUtils.checkNotNullFields;

public abstract class AbstractValidator<A extends Annotation, Dto> implements ConstraintValidator<A, Dto> {

    @Override
    public boolean isValid(final Dto value, final ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (value == null) {
            addMessageToContext(getClassName() + " must be not null", context);
            return false;
        }
        var valid = checkNotNullFields(value, context);

        if (valid) {
            valid = checkValid(value, context);
        }

        return valid;
    }

    protected abstract boolean checkValid(final Dto value, final ConstraintValidatorContext context);

    protected abstract String getClassName();
}
