package com.arttu.mvc.controller;

import com.arttu.mvc.model.Client;
import com.arttu.mvc.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/client")
@RestController
public class ClientController {

    private static final Logger LOGGER = LogManager.getLogger(ClientController.class);

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/findAll")
    public List<Client> findAll() {
        return null;
    }

    @GetMapping(value = "/add")
    public void add() {
        LOGGER.info("Add method started to work");
        Client client = new Client();
        client.setUsername("qwerty");
        client.setPassword("qwerty");
        client.setLastName("q");
        client.setFirstName("a");
        client.setEmail("qwe");
        client.setPhone(911);

        clientService.add(client);
    }

    @PostMapping(value = "/edit")
    public void edit() {}
}
