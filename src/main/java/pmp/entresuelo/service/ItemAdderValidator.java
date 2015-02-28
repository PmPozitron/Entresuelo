/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmp.entresuelo.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pmp.entresuelo.core.Item;
import pmp.entresuelo.core.ItemAdder;

/**
 *
 * @author Pozitron
 */
//@Component ("itemAdderValidator")
public class ItemAdderValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ItemAdder.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "item.name", "name.empty", "Название не может быть пустым.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "item.description", "description.empty", "Описание не может быть пустым.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "item.locationId", "location.empty", "Расположение не может быть пустым.");
        
        Item item = ((ItemAdder)target).getItem();
        if (item.getName().equals("---")) {
            errors.rejectValue("item.name", "name.meaningless", "Введите осмысленное название.");            
        }   // end if

        if (item.getDescription().equals("---")) {
            errors.rejectValue("item.description", "description.meaningless", "Введите осмысленное описание.");            
        }   // end if

        if (item.getLocationId() == -1 || item.getLocationId() == 0){
            errors.rejectValue("item.locationId", "location.empty", "Выберите местоположение.");            
        }   // end if
        
    }
    
}
