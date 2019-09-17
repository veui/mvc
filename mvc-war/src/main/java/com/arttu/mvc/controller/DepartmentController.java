package com.arttu.mvc.controller;

import com.arttu.mvc.exception.department.DepartmentNotFoundException;
import com.arttu.mvc.exception.specialty.SpecialtyNotFoundException;
import com.arttu.mvc.model.Department;
import com.arttu.mvc.model.Specialty;
import com.arttu.mvc.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        List<Department> findAll = departmentService.findAll();
        modelAndView.addObject("departmentList", findAll);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping("/department/find/{id}")
    public ModelAndView findById(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("department/department");
        Department department = departmentService.findById(id);
        List<Specialty> specialties = departmentService.findAttachedSpecialties(id);
        if (department == null) {
            LOGGER.error("Exception in 'findById(int id)' method has been triggered");
            throw new DepartmentNotFoundException("Department not found.");
        }
        if (specialties.isEmpty()) {
            LOGGER.error("Exception in 'findById(int id)' method has been triggered");
            throw new SpecialtyNotFoundException("Specialty not found");
        }
        modelAndView.addObject("department", department);
        modelAndView.addObject("specialtyList", specialties);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping("/department/add")
    public ModelAndView add() {
        return new ModelAndView("department/addDepartment");
    }

    @GetMapping(value = "/department/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("department/editDepartment");
        Department department = departmentService.findById(id);
        if (department.getId() == 0) {
            LOGGER.error("Exception in 'edit(int id)' method has been triggered");
            throw new DepartmentNotFoundException("Department not found.");
        }
        modelAndView.addObject("departmentList", department);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }
}
