package com.arttu.mvc.controller;

import com.arttu.mvc.model.ItemSpecialtyWrapper;
import com.arttu.mvc.service.SpecialtyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private static final Logger LOGGER = LogManager.getLogger(MainController.class);
    private final SpecialtyService specialtyService;

    @Autowired
    public MainController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @GetMapping(value = "/")
    public ModelAndView hello() {
        LOGGER.info("hello method of MainController started to work");
        ModelAndView modelAndView = new ModelAndView("index");
        List<ItemSpecialtyWrapper> itemSpecialtyWrappers = specialtyService.hierarchicalSpecialty();
        modelAndView.addObject("hierarchical", itemSpecialtyWrappers);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView hierarchical(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("index");
        List<ItemSpecialtyWrapper> items = specialtyService.hierarchicalSpecialtyId(id);
        modelAndView.addObject("hierarchical", items);
        return modelAndView;
    }
}
