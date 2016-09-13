package org.hibernate.demo.validation.customer;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.demo.validation.annotation.Status;


public class StatusValidator implements ConstraintValidator<Status, String> {
    private final String[] ALL_STATUS = { "created", "paid", "shipped", "closed" };

    public void initialize(Status status) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Arrays.asList(ALL_STATUS).contains(value))
            return true;
        return false;
    }
}
