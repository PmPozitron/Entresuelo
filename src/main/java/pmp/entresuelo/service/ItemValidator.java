/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmp.entresuelo.service;

import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;
import java.util.Date;
import org.apache.log4j.Level;
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
@Component("itemValidator")
public class ItemValidator implements Validator {

    private static Logger logger = Logger.getLogger(ItemValidator.class);
    private static ConsoleAppender consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_ERR);

    private static void initLogger() {
        ItemValidator.logger.addAppender(ItemValidator.consoleLog);
        ItemValidator.logger.setLevel(Level.ALL);

        ItemValidator.logger.debug(new Date() + " ItemValidator.initLogger() {}");
    }   // end private static void initLogger() {}

    static {
        ItemValidator.initLogger();
    }   // end static

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(Item.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "Название не может быть пустым.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.empty", "Описание не может быть пустым.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "locationId", "location.empty", "Расположение не может быть пустым.");
        
        Item item = (Item)o;
        if (item.getName().equals("---")) {
            errors.rejectValue("name", "name.meaningless", "Введите осмысленное название.");            
        }   // end if

        if (item.getDescription().equals("---")) {
            errors.rejectValue("description", "description.meaningless", "Введите осмысленное описание.");            
        }   // end if

        if (item.getLocationId() == -1 || item.getLocationId() == 0){
            errors.rejectValue("locationId", "location.empty", "Выберите местоположение.");            
        }   // end if

    }

}
