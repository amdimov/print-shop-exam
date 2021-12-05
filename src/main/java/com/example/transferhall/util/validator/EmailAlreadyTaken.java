package com.example.transferhall.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
@Documented
@Constraint(validatedBy = EmailAlreadyTakenValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAlreadyTaken {
    String message() default "Email already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
