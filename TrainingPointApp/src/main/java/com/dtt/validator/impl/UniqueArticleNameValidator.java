package com.dtt.validator.impl;

import com.dtt.services.ArticleService;
import com.dtt.validator.UniqueArticleName;
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

public class UniqueArticleNameValidator implements ConstraintValidator<UniqueArticleName, String>{
    @Autowired
    private ArticleService articleService;

    @Override
    public void initialize(UniqueArticleName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return articleService.findByName(value) == null;
    }
    
}
