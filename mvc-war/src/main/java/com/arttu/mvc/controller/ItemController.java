package com.arttu.mvc.controller;

import com.arttu.mvc.exception.item.ItemNotFoundException;
import com.arttu.mvc.exception.specialty.SpecialtyNotFoundException;
import com.arttu.mvc.model.Item;
import com.arttu.mvc.model.Specialty;
import com.arttu.mvc.service.ItemService;
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
public class ItemController {

    private static final Logger LOGGER = LogManager.getLogger(ItemController.class);
    private final ItemService itemService;
    private final SpecialtyService specialtyService;

    @Autowired
    public ItemController(ItemService itemService, SpecialtyService specialtyService) {
        this.itemService = itemService;
        this.specialtyService = specialtyService;
    }

    @GetMapping("/item")
    public ModelAndView item() {
        ModelAndView modelAndView = new ModelAndView("item/item");
        List<Item> itemList = itemService.findAll();
        modelAndView.addObject("itemList", itemList);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping("/item/find/{id}")
    public ModelAndView findById(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("item/item");
        Item item = itemService.findById(id);
        if (item.getId() == 0) {
            LOGGER.error("findByID(int id) method of ItemController class has been triggered");
            throw new ItemNotFoundException("Item not found");
        }
        modelAndView.addObject("item", item);
        return modelAndView;
    }

    @GetMapping("/item/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("item/addItem");
        List<Specialty> specialties = specialtyService.findAll();
        if (specialties.isEmpty()) throw new SpecialtyNotFoundException("Specialty not found");
        modelAndView.addObject("specialtyList", specialties);
        return modelAndView;
    }

    @GetMapping(value = "/item/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("item/editItem");
        List<Specialty> specialties = specialtyService.findAll();
        Item item = itemService.findById(id);
        if (specialties.isEmpty()) throw new SpecialtyNotFoundException("Specialty not found");
        if (item.getId() == 0) throw new ItemNotFoundException("Item not found");
        modelAndView.addObject("specialtyList", specialties);
        modelAndView.addObject("itemList", item);
        return modelAndView;
    }

    @GetMapping(value = "/item/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        Item item = itemService.findById(id);
        if (item.getId() == 0) {
            throw new ItemNotFoundException("Item not found");
        } else {
            itemService.deleteById(id);
        }
        return new ModelAndView("redirect:/item");
    }
}
