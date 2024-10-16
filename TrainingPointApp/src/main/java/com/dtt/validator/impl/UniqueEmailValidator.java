package com.dtt.validator.impl;

import com.dtt.services.UserService;
import com.dtt.validator.UniqueEmail;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ThienTu
 */

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{
    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userService.getUserByEmail(value) == null;
    }
    
}
