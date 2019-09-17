package com.arttu.mvc.controller.rest;

import com.arttu.mvc.exception.item.ItemNotFoundRestException;
import com.arttu.mvc.model.Item;
import com.arttu.mvc.service.ItemService;
import com.arttu.mvc.util.ValidationHelper;
import com.arttu.mvc.validator.ItemValidator;
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
public class ItemRestController {

    private static final Logger LOGGER = LogManager.getLogger(ItemRestController.class);
    private final ItemService itemService;
    private final ItemValidator itemValidator;

    @Autowired
    public ItemRestController(ItemService itemService, ItemValidator itemValidator) {
        this.itemService = itemService;
        this.itemValidator = itemValidator;
    }

    @InitBinder
    public void dataBind(WebDataBinder dataBinder) {
        dataBinder.setValidator(itemValidator);
    }

    @PostMapping(value = "/item/add", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> add(@RequestBody Item item,
                                                   BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        boolean itemEdited = itemService.editItem(item);
        if (itemEdited) {
            response.put("message", "OK");
        } else throw new ItemNotFoundRestException();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/item/edit", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Item item,
                                                    BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        itemValidator.validate(item, result);
        if (result.hasErrors()) {
            LOGGER.error("Edit method of ItemRestController not unique");
            ValidationHelper.validation(result);
        } else {
            itemService.edit(item);
            response.put("message", "OK");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/item/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        Item item = itemService.findById(id);
        if (item == null) throw new ItemNotFoundRestException();
        else {
            itemService.deleteById(id);
            response.put("message", "OK");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/item/rest/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable int id) {
        Item item = itemService.findById(id);
        if (item == null) throw new ItemNotFoundRestException();
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping(value = "/item/rest/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        List<Item> items = itemService.findAll();
        if (items.isEmpty()) throw new ItemNotFoundRestException();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
