package com.arttu.mvc.validator;

import com.arttu.mvc.model.Item;
import com.arttu.mvc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    private final ItemService service;

    @Autowired
    public ItemValidator(ItemService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Item.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Item item = (Item) o;

        Item itemCheckTitleUniqueness = service.findByTitle(item.getItem());

        if (itemCheckTitleUniqueness.getItem().equals(item.getItem())) {
            errors.rejectValue("item", null, "item");
        }
    }
}
