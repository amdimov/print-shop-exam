package com.example.transferhall.util.validator;

import com.example.transferhall.models.UsersEntity;
import com.example.transferhall.repository.UserRepository;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class WrongUserOrNotExistingValidator
        implements ConstraintValidator<WrongUserOrNotExisting, Object> {
    private String field;
    private String fieldMatch;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public WrongUserOrNotExistingValidator(UserRepository userRepository,
                                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initialize(WrongUserOrNotExisting constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value,
                           ConstraintValidatorContext context) {

        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);

        if (fieldValue == null || fieldMatchValue == null) {
            return false;
        }
        Optional<UsersEntity> user = this.userRepository.findUsersEntityByEmail(fieldValue.toString());
        if (user.isPresent() &&
                passwordEncoder.matches(fieldMatchValue.toString(), user.get().getPassword())) {
            return true;
        }else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode( this.fieldMatch ).addConstraintViolation();
            return false;
        }
    }
}
