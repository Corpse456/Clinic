package by.gp.clinic.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

public class ValidationUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationUtils.class);

    public static void addMessageToContext(final String property,
                                           final String message,
                                           final ConstraintValidatorContext context) {
        context.buildConstraintViolationWithTemplate(message)
            .addPropertyNode(property)
            .addConstraintViolation();
    }

    public static void addMessageToContext(final String message,
                                           final ConstraintValidatorContext context) {
        context.buildConstraintViolationWithTemplate(message)
            .addConstraintViolation();
    }

    public static boolean checkNotNullFields(final Object someObject, final ConstraintValidatorContext context) {
        boolean flag = true;
        final Class<?> someObjectClass = someObject.getClass();
        final Field[] declaredFields = someObjectClass.getDeclaredFields();
        for (final Field field : declaredFields) {
            if (field.isAnnotationPresent(NotNull.class) && getFieldValueIsNull(field, someObject)) {
                addMessageToContext(field.getName(), field.getName() + " must be not null", context);
                flag = false;
            }
        }
        return flag;
    }

    private static boolean getFieldValueIsNull(final Field field, final Object someObject) {
        try {
            field.setAccessible(true);
            return field.get(someObject) == null;
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
            LOGGER.error("Can't execute field " + field.getName());
            return true;
        }
    }
}
