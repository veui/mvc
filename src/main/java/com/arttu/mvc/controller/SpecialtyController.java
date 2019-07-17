package com.arttu.mvc.controller;

import com.arttu.mvc.model.Specialty;
import com.arttu.mvc.service.DepartmentService;
import com.arttu.mvc.service.SpecialtyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SpecialtyController {

    private static final Logger LOGGER = LogManager.getLogger(SpecialtyController.class);
    private SpecialtyService specialtyService;
    private DepartmentService departmentService;

    @Autowired
    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/specialty")
    public ModelAndView department() {
        ModelAndView modelAndView = new ModelAndView("specialty/specialty");
        modelAndView.addObject("specialtyList", specialtyService.findAll());
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping("/specialty/find/{id}")
    public ModelAndView findById(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("specialty/specialty");
        modelAndView.addObject("specialty", specialtyService.findById(id));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping("/specialty/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("specialty/addSpecialty");
        modelAndView.addObject("departmentList", departmentService.findAll());
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @PostMapping(value = "/specialty/add", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> add(@RequestBody Specialty specialty) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            specialtyService.add(specialty);
            response.put("stat", 1);
        } catch (Exception e) {
            LOGGER.error(e);
            response.put("stat", 0);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, status);
    }

    @GetMapping(value = "/specialty/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("specialty/editSpecialty");
        modelAndView.addObject("specialtyList", specialtyService.findById(id));
        modelAndView.addObject("departmentList", departmentService.findAll());
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @PostMapping(value = "/specialty/edit", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Specialty specialty) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            specialtyService.edit(specialty);
            response.put("stat", 1);
        } catch (Exception e) {
            LOGGER.error(e);
            response.put("stat", 0);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, status);
    }

    @GetMapping(value = "/specialty/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        specialtyService.deleteById(id);
        return new ModelAndView("redirect:/specialty");
    }
}
