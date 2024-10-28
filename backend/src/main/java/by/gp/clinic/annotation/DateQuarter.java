package by.gp.clinic.annotation;

import by.gp.clinic.validation.DateQuarterValidator;
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
@Constraint(validatedBy = {DateQuarterValidator.class})
public @interface DateQuarter {

    String message() default "Ticket date must be a multiple of 15";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
