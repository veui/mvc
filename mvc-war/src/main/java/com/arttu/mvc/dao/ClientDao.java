package com.arttu.mvc.dao;

import com.arttu.mvc.model.Client;

public interface ClientDao extends BaseDao<Client> {
    void deleteById(int id);
}