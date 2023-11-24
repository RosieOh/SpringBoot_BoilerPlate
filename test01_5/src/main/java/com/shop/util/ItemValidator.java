package com.shop.util;

import com.shop.domain.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        //item == clazz

    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;



        //검증 로직
        if (!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName", "required");

        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.rejectValue("price", "range", new Object[]{100, 1000000}, null);
        }
        if (item.getQuantity() == null || item.getQuantity() > 9999) {
            errors.rejectValue("quantity", "max", new Object[]{9999},null);
        }



    }
}
