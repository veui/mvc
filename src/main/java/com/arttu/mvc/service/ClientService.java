package com.arttu.mvc.service;

import com.arttu.mvc.model.Client;

import java.util.List;

public interface ClientService {
    void add(Client client);
    void delete(Client client);
    void edit(Client client);
    List<Client> findAll();
    Client findById(int id);
}
