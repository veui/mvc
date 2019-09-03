package com.arttu.mvc.validator;

import com.arttu.mvc.model.Client;
import com.arttu.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClientValidator implements Validator {

    private final ClientService clientService;

    @Autowired
    public ClientValidator(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Client client = (Client) o;

        Client clientCheckUsername = clientService.findByUsername(client.getUsername());
        Client clientCheckEmail = clientService.findByEmail(client.getEmail());

        if (clientCheckUsername.getUsername() != null) {
            errors.rejectValue("username", null, "username");
        }
        if (clientCheckEmail.getEmail() != null) {
            errors.rejectValue("email", null, "email");
        }
    }
}
