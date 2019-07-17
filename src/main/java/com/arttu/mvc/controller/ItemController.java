package com.arttu.mvc.controller;

import com.arttu.mvc.model.Item;
import com.arttu.mvc.service.ItemService;
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
public class ItemController {

    private static final Logger LOGGER = LogManager.getLogger(ItemController.class);
    private ItemService itemService;
    private SpecialtyService specialtyService;

    @Autowired
    public ItemController(ItemService itemService, SpecialtyService specialtyService) {
        this.itemService = itemService;
        this.specialtyService = specialtyService;
    }

    @GetMapping("/item")
    public ModelAndView item() {
        ModelAndView modelAndView = new ModelAndView("item/item");
        modelAndView.addObject("itemList", itemService.findAll());
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping("/item/find/{id}")
    public ModelAndView findById(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("item/item");
        modelAndView.addObject("item", itemService.findById(id));
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping("/item/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("item/addItem");
        modelAndView.addObject("specialtyList", specialtyService.findAll());
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @PostMapping(value = "/item/add", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> add(@RequestBody Item item) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            itemService.add(item);
            response.put("stat", 1);
        } catch (Exception e) {
            LOGGER.error(e);
            response.put("stat", 0);
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(response, status);
    }

    @GetMapping(value = "/item/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("item/editItem");
        modelAndView.addObject("specialtyList", specialtyService.findAll());
        modelAndView.addObject("itemList", itemService.findById(id));
        return modelAndView;
    }

    @PostMapping(value = "/item/edit", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Item item) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            itemService.edit(item);
            response.put("stat", 1);
        } catch (Exception e) {
            LOGGER.error(e);
            response.put("stat", 0);
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, status);
    }

    @GetMapping(value = "/item/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {

        itemService.deleteById(id);
        return new ModelAndView("redirect:/item");
    }
}
