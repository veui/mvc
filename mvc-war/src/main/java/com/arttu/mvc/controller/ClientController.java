package com.arttu.mvc.controller;

import com.arttu.mvc.exception.client.ClientNotFoundException;
import com.arttu.mvc.model.Client;
import com.arttu.mvc.service.ClientService;
import com.arttu.mvc.util.ValidationHelper;
import com.arttu.mvc.validator.ClientValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ClientController {

    private static final Logger LOGGER = LogManager.getLogger(ClientController.class);

    private final ClientService clientService;
    private final ClientValidator clientValidator;

    @Autowired
    public ClientController(ClientService clientService, ClientValidator clientValidator) {
        this.clientService = clientService;
        this.clientValidator = clientValidator;
    }

    @InitBinder
    public void dataBind(WebDataBinder binder) {
        binder.addValidators(clientValidator);
    }

    @GetMapping(value = "/client")
    public ModelAndView findAll() {
        LOGGER.info("Find all method started to work");
        ModelAndView modelAndView = new ModelAndView("client/client");
        List<Client> list;
        try {
            list = clientService.findAll();
        } catch (ClientNotFoundException ex) {
            throw new ClientNotFoundException();
        }
        modelAndView.addObject("clientList", list);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping(value = "/client/find/{id}")
    public ModelAndView findById(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("client/client");
        Client client;
        try {
            client = clientService.findById(id);
        } catch (ClientNotFoundException ex) {
            throw new ClientNotFoundException();
        }
        modelAndView.addObject("client", client);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping(value = "/client/add")
    public ModelAndView add() {
        LOGGER.info("Add method(GET) started to work");
        return new ModelAndView("client/addClient");
    }

    @PostMapping(value = "/client/add", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> add(@RequestBody Client client,
                                                   BindingResult result) {
        LOGGER.info("Add method(POST) started to work");
        Map<String, Object> response = new HashMap<>();
        clientValidator.validate(client, result);
        if (result.hasErrors()) {
            ValidationHelper.validation(result);
        } else {
            clientService.add(client);
            response.put("message", "OK");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/client/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        LOGGER.info("Edit method(GET) started to work");
        ModelAndView modelAndView = new ModelAndView("client/editClient");
        try {
            clientService.findById(id);
        } catch (ClientNotFoundException ex){
            throw new ClientNotFoundException();
        }
        modelAndView.addObject("clientList", clientService.findById(id));
        return modelAndView;
    }

    @PostMapping(value = "/client/edit", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Client client,
                                                    BindingResult result) {
        LOGGER.info("Edit method(POST) started to work");
        Map<String, Object> response = new HashMap<>();
        clientValidator.validate(client, result);
        if (result.hasErrors()) {
            ValidationHelper.validation(result);
        } else {
            clientService.edit(client);
            response.put("message", "OK");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/client/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        clientService.deleteById(id);
        return new ModelAndView("redirect:/client");
    }
}
