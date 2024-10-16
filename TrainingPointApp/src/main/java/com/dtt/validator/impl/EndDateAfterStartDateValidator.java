package com.dtt.validator.impl;

import com.dtt.pojo.Activity;
import com.dtt.validator.EndDateAfterStartDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author ThienTu
 */

public class EndDateAfterStartDateValidator implements ConstraintValidator<EndDateAfterStartDate, Activity> {
    @Override
    public void initialize(EndDateAfterStartDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(Activity activity, ConstraintValidatorContext context) {
        if (activity.getStartDateTime() == null || activity.getEndDate() == null) {
            return true;
        }
        return activity.getEndDate().after(activity.getStartDateTime());
    }
}
