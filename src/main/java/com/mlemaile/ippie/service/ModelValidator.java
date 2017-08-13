package com.mlemaile.ippie.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mlemaile.ippie.service.dto.ModelDto;

@Component
public class ModelValidator implements Validator {

    @Override
    public boolean supports ( Class<?> clazz ) {
        return ModelDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate ( Object target, Errors errors ) {
        ValidationUtils.rejectIfEmpty(errors, "name",
                "The name can't be empty or only white space.");
        ModelDto dto = (ModelDto) target;
        if (dto.getTypeId() <= 0) {
            errors.reject("The type idedntifier must be greater than 0.");
        }
    }

}
