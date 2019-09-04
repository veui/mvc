package com.arttu.mvc.util;

import com.arttu.mvc.exception.client.ClientIsNotUniqueRestException;
import com.arttu.mvc.exception.client.EmailIsNotUniqueRestException;
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
            if (s.equals("username")) throw new ClientIsNotUniqueRestException();
            if (s.equals("email")) throw new EmailIsNotUniqueRestException();
        }
    }
}
