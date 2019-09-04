package com.arttu.mvc.controller.rest;

import com.arttu.mvc.model.Client;
import com.arttu.mvc.service.ClientService;
import com.arttu.mvc.util.ValidationHelper;
import com.arttu.mvc.validator.ClientValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ClientRestController {

    private static final Logger LOGGER = LogManager.getLogger(ClientRestController.class);

    private final ClientService clientService;
    private final ClientValidator clientValidator;

    public ClientRestController(ClientService clientService, ClientValidator clientValidator) {
        this.clientService = clientService;
        this.clientValidator = clientValidator;
    }

    @InitBinder
    public void dataBind(WebDataBinder dataBinder) {
        dataBinder.setValidator(clientValidator);
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
}
