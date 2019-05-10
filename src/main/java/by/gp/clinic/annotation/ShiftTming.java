package by.gp.clinic.annotation;

import by.gp.clinic.validation.ShiftTimingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
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
@Constraint(validatedBy = {ShiftTimingValidator.class})
public @interface ShiftTming {

    String message() default "Wrong shift timing";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
