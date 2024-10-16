/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.validator;

import com.dtt.validator.impl.EndDateAfterStartDateValidator;
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

@Constraint(validatedBy = EndDateAfterStartDateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EndDateAfterStartDate {
    String message() default "{activity.endDateAfterStartDate.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
