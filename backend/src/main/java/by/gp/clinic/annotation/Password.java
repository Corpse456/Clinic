package by.gp.clinic.annotation;

import by.gp.clinic.validation.PasswordValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, CONSTRUCTOR, PARAMETER, FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {PasswordValidator.class})
public @interface Password {

    String message() default "Password doesn't strong enough";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
