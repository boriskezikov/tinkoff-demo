package tinkoff.demo.exceptions;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=IdTypeConstraintValidator.class)
public @interface EntryDataConstraint {
    String message() default "Id must be instanse of Integer! ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
