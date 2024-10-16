/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.validator;

import com.dtt.validator.impl.SemesterDateValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author ThienTu
 */

@Constraint(validatedBy = SemesterDateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SemesterDate {
    String message() default "{semester.endDateAfterStartDate.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
