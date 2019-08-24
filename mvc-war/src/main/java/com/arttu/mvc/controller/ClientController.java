package com.arttu.mvc.controller;

import com.arttu.mvc.model.Client;
import com.arttu.mvc.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@RestController
public class ClientController {

    private static final Logger LOGGER = LogManager.getLogger(ClientController.class);

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/client")
    public ModelAndView findAll() {
        LOGGER.info("Find all method started to work");
        ModelAndView modelAndView = new ModelAndView("client/client");
        List<Client> list = clientService.findAll();
        modelAndView.addObject("clientList", list);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping(value = "/client/find/{id}")
    public ModelAndView findById(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("client/client");
        Client client = clientService.findById(id);
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
    public ResponseEntity<Map<String, Object>> add(@RequestBody Client client) {
        LOGGER.info("Add method(POST) started to work");
        Map<String, Object> response = new HashMap<>();

        Predicate<Client> checkUsername = cl -> cl.getUsername().equals(client.getUsername());
        Predicate<Client> checkEmail = cl -> cl.getEmail().equals(client.getEmail());
        Predicate<Client> checkPhone = cl -> cl.getPhone() == client.getPhone();

        if (checkUsername.test(client)) {
            response.put("stat", 0);
            response.put("err", "username");
        } else if (checkEmail.test(client)) {
            response.put("stat", 0);
            response.put("err", "email");
        } else if (checkPhone.test(client)) {
            response.put("stat", 0);
            response.put("err", "phone");
        } else {
            clientService.add(client);
            response.put("stat", 1);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/client/edit/{id}")
    public ModelAndView edit(@PathVariable int id) throws SQLException {
        LOGGER.info("Edit method(GET) started to work");
        ModelAndView modelAndView = new ModelAndView("client/editClient");
        if (clientService.findById(id) == null) {
            throw new SQLException("Not Found");
        } else {
            modelAndView.addObject("clientList", clientService.findById(id));
        }
        return modelAndView;
    }

    @PostMapping(value = "/client/edit", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Client client) {
        LOGGER.info("Edit method(POST) started to work");
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        clientService.edit(client);
        response.put("stat", 1);
        return new ResponseEntity<>(response, status);
    }

    @GetMapping(value = "/client/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        clientService.deleteById(id);
        return new ModelAndView("redirect:/client");
    }
}
