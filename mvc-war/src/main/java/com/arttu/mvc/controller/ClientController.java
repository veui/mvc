package com.arttu.mvc.controller;

import com.arttu.mvc.exception.client.ClientNotFoundException;
import com.arttu.mvc.model.Client;
import com.arttu.mvc.service.ClientService;
import com.arttu.mvc.validator.ClientValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
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
        List<Client> list = clientService.findAll();
        if (list == null) throw new ClientNotFoundException("Client not found. Please try another one");
        modelAndView.addObject("clientList", list);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping(value = "/client/find/{id}")
    public ModelAndView findById(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("client/client");
        Client client = clientService.findById(id);
        if (client == null) throw new ClientNotFoundException("Client not found. Please try another one");
        modelAndView.addObject("client", client);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @GetMapping(value = "/client/add")
    public ModelAndView add() {
        LOGGER.info("Add method(GET) started to work");
        return new ModelAndView("client/addClient");
    }

    @GetMapping(value = "/client/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        LOGGER.info("Edit method(GET) started to work");
        ModelAndView modelAndView = new ModelAndView("client/editClient");
        Client client = clientService.findById(id);
        if (client == null) throw new ClientNotFoundException("Client not found. Please try another one");
        modelAndView.addObject("clientList", clientService.findById(id));
        return modelAndView;
    }

    @GetMapping(value = "/client/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        Client client = clientService.findById(id);
        if (client == null) {
            throw new ClientNotFoundException("Client not found. Please try another one");
        } else {
            clientService.deleteById(id);
        }
        return new ModelAndView("redirect:/client");
    }
}
