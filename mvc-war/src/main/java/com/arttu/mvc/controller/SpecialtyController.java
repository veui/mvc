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
        List<Specialty> specialties = specialtyService.findAll();
        if (specialties == null) {
            LOGGER.error("department() method of SpecailtyController class");
            throw new SpecialtyNotFoundException("Specialty not found");
        }
        modelAndView.addObject("specialtyList", specialties);
        return modelAndView;
    }

    @GetMapping("/specialty/find/{id}")
    public ModelAndView findById(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("specialty/specialty");
        Specialty specialty = specialtyService.findById(id);
        List<Item> itemList = specialtyService.findAttachedItems(id);
        if (specialty == null) {
            LOGGER.error("findById method of SpecialtyController class");
            throw new SpecialtyNotFoundException("Specialty not found");
        }
        if (itemList == null) {
            LOGGER.error("findById method of SpecialtyController class");
            throw new SpecialtyNotFoundException("Item not found");
        }
        modelAndView.addObject("specialty", specialty);
        modelAndView.addObject("itemList", itemList);
        return modelAndView;
    }

    @GetMapping("/specialty/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("specialty/addSpecialty");
        List<Department> departments = departmentService.findAll();
        if (departments == null) throw new DepartmentNotFoundException("Department not found");
        modelAndView.addObject("departmentList", departments);
        return modelAndView;
    }

    @GetMapping(value = "/specialty/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("specialty/editSpecialty");
        Specialty specialty = specialtyService.findById(id);
        List<Department> departments = departmentService.findAll();
        if (specialty == null) {
            LOGGER.error("Edit method of SpecialtyController class");
            throw new SpecialtyNotFoundException("Specialty not found");
        }
        if (departments == null) {
            LOGGER.error("Edit method of SpecialtyController class");
            throw new DepartmentNotFoundException("Department not found");
        }
        modelAndView.addObject("specialtyList", specialty);
        modelAndView.addObject("departmentList", departments);
        return modelAndView;
    }

    @GetMapping(value = "/specialty/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        Specialty specialty = specialtyService.findById(id);
        if (specialty == null) {
            throw new SpecialtyNotFoundException("Specialty not found");
        } else {
            specialtyService.deleteById(id);
        }
        return new ModelAndView("redirect:/specialty");
    }
}
