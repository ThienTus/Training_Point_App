/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtt.validator;

import com.dtt.validator.impl.UniqueFacultyNameValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author ThienTu
 */

@Constraint(validatedBy = UniqueFacultyNameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueFacultyName {
    String message() default "{faculty.UniqueName.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
