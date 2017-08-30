package com.mlemaile.ippie.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mlemaile.ippie.service.dto.ComponentDto;

@Component
public class ComponentValidator implements Validator {

    @Override
    public boolean supports ( Class<?> clazz ) {
        return ComponentDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate ( Object target, Errors errors ) {
        ValidationUtils.rejectIfEmpty(errors, "name",
                "The name can't be null, empty or white space.");
        // TODO for other rules, see with PO.
    }

}
