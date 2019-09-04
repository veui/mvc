package com.arttu.mvc.service.serviceImpl;

import com.arttu.mvc.dao.ClientDao;
import com.arttu.mvc.model.Client;
import com.arttu.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public void add(Client client) {
        clientDao.add(client);
    }

    @Override
    public void delete(Client client) {
        clientDao.delete(client);
    }

    @Override
    public void edit(Client client) {
        clientDao.edit(client);
    }

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Client findById(int id) {
        return clientDao.findById(id);
    }

    @Override
    public Client findByEmail(String email) {
        return clientDao.findByEmail(email);
    }

    @Override
    public Client findByUsername(String username) {
        return clientDao.findByUsername(username);
    }

    @Override
    public boolean deleteById(int id) {
        return clientDao.deleteById(id);
    }
}
