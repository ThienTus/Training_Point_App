package com.dtt.validator.impl;

import com.dtt.pojo.Semester;
import com.dtt.validator.SemesterDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author ThienTu
 */

public class SemesterDateValidator implements ConstraintValidator<SemesterDate, Semester> {
    @Override
    public void initialize(SemesterDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(Semester semester, ConstraintValidatorContext context) {
        if (semester.getStartDate()== null || semester.getEndDate() == null) {
            return true;
        }
        return semester.getEndDate().after(semester.getStartDate());
    }
}
