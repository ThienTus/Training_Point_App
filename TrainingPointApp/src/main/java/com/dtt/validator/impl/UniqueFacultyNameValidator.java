package com.dtt.validator.impl;

import com.dtt.services.FacultyService;
import com.dtt.validator.UniqueFacultyName;
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

public class UniqueFacultyNameValidator implements ConstraintValidator<UniqueFacultyName, String>{
    @Autowired
    private FacultyService facultyService;

    @Override
    public void initialize(UniqueFacultyName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return facultyService.findByName(value) == null;
    }
    
}
