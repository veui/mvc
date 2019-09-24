package com.arttu.mvc.controller;

import com.arttu.mvc.exception.department.DepartmentNotFoundException;
import com.arttu.mvc.exception.specialty.SpecialtyNotFoundException;
import com.arttu.mvc.model.Department;
import com.arttu.mvc.model.Item;
import com.arttu.mvc.model.Specialty;
import com.arttu.mvc.service.DepartmentService;
import com.arttu.mvc.service.SpecialtyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SpecialtyController {

    private static final Logger LOGGER = LogManager.getLogger(SpecialtyController.class);
    private final SpecialtyService specialtyService;
    private final DepartmentService departmentService;

    @Autowired
    public SpecialtyController(SpecialtyService specialtyService, DepartmentService departmentService) {
        this.specialtyService = specialtyService;
        this.departmentService = departmentService;
    }

    @GetMapping("/specialty")
    public ModelAndView department() {
        ModelAndView modelAndView = new ModelAndView("specialty/specialty");
        List<Specialty> specialties = specialtyService.findAll();
        List<Department> departments = departmentService.findAll();
        modelAndView.addObject("specialtyList", specialties);
        modelAndView.addObject("departmentList", departments);
        return modelAndView;
    }

    @GetMapping("/specialty/find/{id}")
    public ModelAndView findById(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("specialty/specialty");
        Specialty specialty = specialtyService.findById(id);
        List<Specialty> specialties = specialtyService.findAll();
        List<Item> itemList = specialtyService.findAttachedItems(id);
        List<Department> departments = departmentService.findAll();
        LOGGER.info(specialty.getId() + " " + specialty.toString());
        if (specialty.getId() == 0) {
            LOGGER.error("findById method of SpecialtyController class");
            throw new SpecialtyNotFoundException("Specialty not found");
        }
        modelAndView.addObject("specialty", specialty);
        modelAndView.addObject("specialtyList", specialties);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("departmentList", departments);
        return modelAndView;
    }

    @GetMapping("/specialty/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("specialty/addSpecialty");
        List<Department> departments = departmentService.findAll();
        List<Specialty> specialties = specialtyService.findAll();
        if (departments.isEmpty()) throw new DepartmentNotFoundException("Department not found");
        modelAndView.addObject("departmentList", departments);
        modelAndView.addObject("specialtyList", specialties);
        return modelAndView;
    }

    @GetMapping(value = "/specialty/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("specialty/editSpecialty");
        Specialty specialty = specialtyService.findById(id);
        List<Specialty> specialties = specialtyService.findAll();
        List<Department> departments = departmentService.findAll();
        if (specialty.getId() == 0) {
            LOGGER.error("Edit method of SpecialtyController class");
            throw new SpecialtyNotFoundException("Specialty not found");
        }
        if (departments.isEmpty()) {
            LOGGER.error("Edit method of SpecialtyController class");
            throw new DepartmentNotFoundException("Department not found");
        }
        modelAndView.addObject("specialtyList", specialty);
        modelAndView.addObject("specialties", specialties);
        modelAndView.addObject("departmentList", departments);
        return modelAndView;
    }
}
