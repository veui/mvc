package com.arttu.mvc.controller.rest;

import com.arttu.mvc.exception.department.DepartmentNotFoundRestException;
import com.arttu.mvc.model.Department;
import com.arttu.mvc.service.DepartmentService;
import com.arttu.mvc.util.ValidationHelper;
import com.arttu.mvc.validator.DepartmentValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DepartmentRestController {

    private static final Logger LOGGER = LogManager.getLogger(DepartmentRestController.class);

    private final DepartmentService departmentService;
    private final DepartmentValidator departmentValidator;

    private Map<String, Object> response;

    @Autowired
    public DepartmentRestController(DepartmentService departmentService, DepartmentValidator departmentValidator) {
        this.departmentService = departmentService;
        this.departmentValidator = departmentValidator;
    }

    @InitBinder
    public void dataBind(WebDataBinder dataBinder) {
        dataBinder.setValidator(departmentValidator);
    }

    @PostMapping(value = "/department/add", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> add(@RequestBody Department department,
                                                   BindingResult result) {
        LOGGER.info("Add method of DepartmentRestController started to work");
        response = new HashMap<>();
        departmentValidator.validate(department, result);
        if (result.hasErrors()) {
            LOGGER.info("Result has errors");
            ValidationHelper.validation(result);
        } else {
            departmentService.add(department);
            response.put("message", "OK");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "department/edit", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Department department,
                                                    BindingResult result) {
        LOGGER.info("Edit method of DepartmentRestController started to work");
        response = new HashMap<>();
        departmentValidator.validate(department, result);
        if (result.hasErrors()) {
            ValidationHelper.validation(result);
        } else {
            departmentService.edit(department);
            response.put("message", "OK");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/department/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        Department department = departmentService.findById(id);
        if (department == null) {
            throw new DepartmentNotFoundRestException();
        } else {
            departmentService.deleteById(id);
            response.put("message", "OK");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/department/rest/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable int id) {
        Department department = departmentService.findById(id);
        if (department == null) throw new DepartmentNotFoundRestException();
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
    @GetMapping(value = "/department/rest/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        List<Department> departments = departmentService.findAll();
        if (departments.isEmpty()) throw new DepartmentNotFoundRestException();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}
