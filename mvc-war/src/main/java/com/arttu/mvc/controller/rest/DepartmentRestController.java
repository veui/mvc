package com.arttu.mvc.controller.rest;

import com.arttu.mvc.model.Department;
import com.arttu.mvc.service.DepartmentService;
import com.arttu.mvc.validator.DepartmentValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    @PostMapping(value = "/department/add", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> add(@RequestBody Department department) {
        response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            departmentService.add(department);
            response.put("stat", 1);
        } catch (Exception e) {
            LOGGER.error(e);
            response.put("stat", 0);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, status);
    }

    @PostMapping(value = "/department/edit", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Department department) {
        response = new HashMap<>();
        try {
            departmentService.edit(department);
            response.put("stat", 1);
        } catch (Exception e) {
            LOGGER.error(e);
            response.put("stat", 0);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
