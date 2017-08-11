package com.mlemaile.ippie.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mlemaile.ippie.service.dto.TypeDto;

@Component
public class TypeValidator implements Validator {

    @Override
    public boolean supports ( Class<?> clazz ) {
        return TypeDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate ( Object target, Errors errors ) {
        ValidationUtils.rejectIfEmpty(errors, "name",
                "The name can't be empty or only white space.");
    }

}
