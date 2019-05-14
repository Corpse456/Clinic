package by.gp.clinic.annotation;

import by.gp.clinic.validation.DateInFutureValidator;

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
@Constraint(validatedBy = {DateInFutureValidator.class})
public @interface DateInFuture {

    String message() default "Date must be in future";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
