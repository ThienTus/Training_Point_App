package com.dtt.validator.impl;

import com.dtt.services.ParticipantService;
import com.dtt.validator.UniqueParticipantName;
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

public class UniqueParticipantNameValidator implements ConstraintValidator<UniqueParticipantName, String>{
    @Autowired
    private ParticipantService participantService;

    @Override
    public void initialize(UniqueParticipantName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return participantService.findByName(value) == null;
    }
    
}
