package com.example.transferhall.models.bindingModels;

import com.example.transferhall.util.validator.EmailAlreadyTaken;
import com.example.transferhall.util.validator.PasswordMatch;
import com.example.transferhall.util.validator.WrongUserOrNotExisting;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.validation.constraints.*;

@PasswordMatch.List({
        @PasswordMatch(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "Password fields don't match. Please try again. (ex. pAssword2)"
        )
})
public class UserRegisterBindingModel {
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 symbols")
    @NotBlank
    private String firstName;

    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 symbols")
    @NotBlank
    private String lastName;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"
            , message = "Password should contain minimum 8 letters, including upper case and one number")
    @NotNull
    private String password;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"
            , message = "Password should contain minimum 8 letters, including upper case and a number")
    @NotNull
    private String confirmPassword;

    @Email(message = "Please write your email")
    @EmailAlreadyTaken
    @NotBlank
    private String email;

    @Size(min = 3, max = 50, message = "Company should be between 3 and 50 symbols")
    @NotBlank(message = "Please write your company name")
    private String companyName;

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public UserRegisterBindingModel setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
