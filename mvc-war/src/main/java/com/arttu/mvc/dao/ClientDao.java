package com.arttu.mvc.dao;

import com.arttu.mvc.model.Client;

public interface ClientDao extends BaseDao<Client> {
    boolean deleteById(int id);
    Client findByEmail(String email);
    Client findByUsername(String username);
    boolean editClient(Client client);
}
