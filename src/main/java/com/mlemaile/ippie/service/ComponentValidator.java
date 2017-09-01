package com.mlemaile.ippie.service;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE;
        ValidationUtils.rejectIfEmpty(errors, "name",
                "The name can't be null, empty or white space.");
        ComponentDto compo = (ComponentDto) target;
        if (compo.getModelId() <= 0) {
            errors.rejectValue("Modèle", "You must specify a Model");
        }
        if (compo.getIntroduced() != null && !compo.getIntroduced().isEmpty()) {
            try {
                df.parse(compo.getIntroduced());
            } catch (DateTimeParseException e) {
                errors.rejectValue("Introduced date",
                        "The date must be in the following format : yyyy-mm-dd");
            }
        }
        if (compo.getDiscontinued() != null && !compo.getDiscontinued().isEmpty()) {
            try {
                df.parse(compo.getDiscontinued());
            } catch (DateTimeParseException e) {
                errors.rejectValue("Discontinued date",
                        "The date must be in the following format : yyyy-mm-dd");
            }
        }
        // TODO Ensure the state is in the posible list
    }

}
