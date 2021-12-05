package com.example.transferhall.util.validator;

import com.example.transferhall.models.UsersEntity;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class PasswordMatchValidator
        implements ConstraintValidator<PasswordMatch, Object> {
    private String field;
    private String fieldMatch;
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);

        if (fieldValue == null && fieldMatchValue == null) {
            return false;
        }
        if (fieldValue.equals(fieldMatchValue)) {
            return true;
        }else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode( this.field ).addConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode( this.fieldMatch ).addConstraintViolation();
            return false;
        }
    }
}
