package com.spring.test.web.dao;

import org.hibernate.validator.constraints.NotEmpty;

import com.spring.test.web.validation.FieldMatch;

@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class PasswordResetForm {

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String token;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
