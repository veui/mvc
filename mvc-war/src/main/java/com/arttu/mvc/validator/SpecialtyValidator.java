package com.arttu.mvc.validator;

import com.arttu.mvc.model.Specialty;
import com.arttu.mvc.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpecialtyValidator implements Validator {

    private final SpecialtyService specialtyService;

    @Autowired
    public SpecialtyValidator(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Specialty.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Specialty specialty = (Specialty) o;

        List<Specialty> checkTitleUniqueness = specialtyService.findAll();
        List<Specialty> result = checkTitleUniqueness
                .stream()
                .filter(sp -> sp.equals(specialty))
                .collect(Collectors.toList());
        if (!result.isEmpty()) {
            errors.rejectValue("specialty", null, "specialty");
        }
    }
}
