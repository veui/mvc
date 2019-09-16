package com.arttu.mvc.controller.rest;

import com.arttu.mvc.exception.client.ClientNotFoundException;
import com.arttu.mvc.exception.client.ClientNotFoundRestException;
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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @PutMapping(value = "/client/edit", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> edit(@RequestBody Client client) {
        LOGGER.info("Edit method(PUT) started to work");
        Map<String, Object> response = new HashMap<>();
        boolean clientEdited = clientService.editClient(client);
        if (clientEdited) {
            response.put("message", "OK");
        } else {
            throw new ClientNotFoundRestException();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "client/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        Client client = clientService.findById(id);
        if (client.getId() == 0) {
            throw new ClientNotFoundException("Client not found");
        } else {
            clientService.deleteById(id);
            response.put("message", "OK");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "client/rest/find/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findById(@PathVariable int id) {
        Client client = clientService.findById(id);
        if (client == null) {
            throw new ClientNotFoundRestException();
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping(value = "client/rest/findAll")
    public ResponseEntity<?> findAll() {
        List<Client> clients = clientService.findAll();
        if (clients.isEmpty()) {
            throw new ClientNotFoundRestException();
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}
