package com.dtt.validator.impl;

import com.dtt.services.ClassService;
import com.dtt.validator.UniqueClassName;
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

public class UniqueClassNameValidator implements ConstraintValidator<UniqueClassName, String>{
    @Autowired
    private ClassService classService;

    @Override
    public void initialize(UniqueClassName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return classService.findByName(value) == null;
    }
    
}
