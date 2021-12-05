package com.example.transferhall.util.validator;

import com.example.transferhall.service.UsersService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailAlreadyTakenValidator implements
        ConstraintValidator<EmailAlreadyTaken, String> {
    private UsersService usersService;

    public EmailAlreadyTakenValidator(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public void initialize(EmailAlreadyTaken constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !this.usersService.isEmailAlreadyTaken(value);
    }
}
