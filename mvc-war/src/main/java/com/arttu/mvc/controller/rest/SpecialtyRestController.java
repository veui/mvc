package com.arttu.mvc.controller.rest;

import com.arttu.mvc.model.Specialty;
import com.arttu.mvc.service.SpecialtyService;
import com.arttu.mvc.util.ValidationHelper;
import com.arttu.mvc.validator.SpecialtyValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SpecialtyRestController {

    private static final Logger LOGGER = LogManager.getLogger(SpecialtyRestController.class);
    private final SpecialtyService specialtyService;
    private final SpecialtyValidator specialtyValidator;

    @Autowired
    public SpecialtyRestController(SpecialtyService specialtyService, SpecialtyValidator specialtyValidator) {
        this.specialtyService = specialtyService;
        this.specialtyValidator = specialtyValidator;
    }

    @InitBinder
    public void dataBind(WebDataBinder dataBinder) {
        dataBinder.setValidator(specialtyValidator);
    }

    @PostMapping(value = "/specialty/add", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> add(@RequestBody Specialty specialty, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        specialtyValidator.validate(specialty, result);
        if (result.hasErrors()) {
            LOGGER.error("Add method of SpecialtyRestController");
            ValidationHelper.validation(result);
        } else {
            specialtyService.add(specialty);
            response.put("message", "OK");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/specialty/edit", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Specialty specialty, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        specialtyValidator.validate(specialty, result);
        if (result.hasErrors()) {
            LOGGER.error("Edit method of SpecialtyRestController");
            ValidationHelper.validation(result);
        } else {
            specialtyService.edit(specialty);
            response.put("message", "OK");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
