package com.arttu.mvc.validator;

import com.arttu.mvc.model.Department;
import com.arttu.mvc.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DepartmentValidator implements Validator {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentValidator(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Department.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Department department = (Department) o;
        Department departmentToCompare = departmentService.findByTitle(department.getTitle());

        if (departmentToCompare.getTitle() != null) {
            errors.rejectValue("depTitle", null, "depTitle");
        }
    }
}
