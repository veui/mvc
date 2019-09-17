package com.arttu.mvc.controller.rest;

import com.arttu.mvc.exception.specialty.SpecialtyNotFoundRestException;
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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @PutMapping(value = "/specialty/edit", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Specialty specialty) {
        Map<String, Object> response = new HashMap<>();
        boolean specialtyEdited = specialtyService.editSpecialty(specialty);
        if (specialtyEdited) {
            response.put("message", "OK");
        } else {
            throw new SpecialtyNotFoundRestException();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/specialty/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable int id) {
        Map<String, Object> resposnse = new HashMap<>();
        Specialty specialty = specialtyService.findById(id);
        if (specialty == null) throw new SpecialtyNotFoundRestException();
        else {
            specialtyService.deleteById(id);
            resposnse.put("message", "OK");
        }
        return new ResponseEntity<>(resposnse, HttpStatus.OK);
    }

    @GetMapping(value = "/specialty/rest/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable int id) {
        Specialty specialty = specialtyService.findById(id);
        if (specialty == null) throw new SpecialtyNotFoundRestException();
        return new ResponseEntity<>(specialty, HttpStatus.OK);
    }

    @GetMapping(value = "/specialty/rest/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        List<Specialty> specialties = specialtyService.findAll();
        if (specialties.isEmpty()) throw new SpecialtyNotFoundRestException();
        return new ResponseEntity<>(specialties, HttpStatus.OK);
    }
}
