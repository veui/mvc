package com.arttu.mvc.util;

import com.arttu.mvc.exception.client.ClientIsNotUniqueException;
import com.arttu.mvc.exception.client.EmailIsNotUniqueException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationHelper {
    public static void validation(BindingResult result) {
        List<String> list = result.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        for (String s : list) {
            if (s.equals("username")) throw new ClientIsNotUniqueException();
            if (s.equals("email")) throw new EmailIsNotUniqueException();
        }
    }
}
