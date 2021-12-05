package com.example.transferhall.models.serviceModels;

//import com.example.transferhall.util.validator.EmailAlreadyTaken;

import javax.validation.constraints.*;

public class UserRegisterServiceModel {
    private String firstName;

    private String lastName;

    private String password;

    private String confirmPassword;

    private String email;

    private String companyName;

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterServiceModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public UserRegisterServiceModel setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }
}
