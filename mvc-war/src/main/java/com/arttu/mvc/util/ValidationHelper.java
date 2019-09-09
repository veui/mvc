package com.arttu.mvc.util;

import com.arttu.mvc.exception.client.ClientIsNotUniqueRestException;
import com.arttu.mvc.exception.client.EmailIsNotUniqueRestException;
import com.arttu.mvc.exception.department.DepartmentIsNotUniqueException;
import com.arttu.mvc.exception.item.ItemIsNotUniqueException;
import com.arttu.mvc.exception.order.OrderIsNotUniqueException;
import com.arttu.mvc.exception.specialty.SpecialtyIsNotUniqueException;
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
            if (s.equals("depTitle")) throw new DepartmentIsNotUniqueException();
            if (s.equals("item")) throw new ItemIsNotUniqueException();
            if (s.equals("order")) throw new OrderIsNotUniqueException();
            if (s.equals("specialty")) throw new SpecialtyIsNotUniqueException();
        }
    }
}
