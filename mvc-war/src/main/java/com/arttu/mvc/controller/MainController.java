package com.arttu.mvc.controller;

import com.arttu.mvc.service.ItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private static final Logger LOGGER = LogManager.getLogger(MainController.class);
    private ItemService itemService;

    @Autowired
    public MainController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/")
    public ModelAndView hello() {
        LOGGER.info("hello method of MainController started to work");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("hierarchicalItem", itemService.findAllHierarchicalSpec());
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }
}
