package tinkoff.demo.exceptions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigInteger;

public class IdTypeConstraintValidator implements ConstraintValidator<EntryDataConstraint, Object> {
    @Override
    public void initialize(EntryDataConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object entryCode, ConstraintValidatorContext constraintValidatorContext) {
        return entryCode instanceof BigInteger;
    }
}
