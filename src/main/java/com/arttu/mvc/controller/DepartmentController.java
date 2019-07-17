package com.arttu.mvc.controller;

import com.arttu.mvc.model.Department;
import com.arttu.mvc.service.DepartmentService;
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
public class DepartmentController {

    private static final Logger LOGGER = LogManager.getLogger(DepartmentController.class);
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department")
    public ModelAndView department() {
        ModelAndView modelAndView = new ModelAndView("department/department");
        modelAndView.addObject("departmentList", departmentService.findAll());
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping("/department/find/{id}")
    public ModelAndView findById(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("department/department");
        modelAndView.addObject("department", departmentService.findById(id));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping("/department/add")
    public ModelAndView add() {
        return new ModelAndView("department/addDepartment");
    }

    @PostMapping("/department/add")
    public ResponseEntity<Map<String, Object>> add(@RequestBody Department department) {
        Map<String, Object> response = new HashMap<>();
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

    @GetMapping(value = "/department/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("department/editDepartment");
        modelAndView.addObject("departmentList", departmentService.findById(id));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @PostMapping(value = "/department/edit", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Department department) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            departmentService.edit(department);
            response.put("stat", 1);
        } catch (Exception e) {
            LOGGER.error(e);
            response.put("stat", 0);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, status);
    }

    @GetMapping(value = "/department/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        departmentService.deleteById(id);
        return new ModelAndView("redirect:/department");
    }
}
