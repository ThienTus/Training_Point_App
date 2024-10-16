package com.dtt.validator.impl;

import com.dtt.services.SemesterService;
import com.dtt.validator.UniqueSemesterName;
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

public class UniqueSemesterNameValidator implements ConstraintValidator<UniqueSemesterName, Integer>{
    @Autowired
    private SemesterService semesterService;

    @Override
    public void initialize(UniqueSemesterName constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return semesterService.findByName(value) == null;
    }
    
}
