/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmp.entresuelo.service;

import java.util.Date;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pmp.entresuelo.core.Location;

/**
 *
 * @author Pozitron
 */
@Component("locationValidator")
public class LocationValidator implements Validator {

    private static Logger logger = Logger.getLogger(LocationValidator.class);
    private static ConsoleAppender consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_ERR);

    private static void initLogger() {
        LocationValidator.logger.addAppender(LocationValidator.consoleLog);
        LocationValidator.logger.setLevel(Level.ALL);

        LocationValidator.logger.debug(new Date() + " LocationValidator.initLogger() {}");
    }   // end private static void initLogger() {}

    static {
        LocationValidator.initLogger();
    }   // end static

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Location.class);
    }   // end public boolean supports(Class<?> clazz) {}

    @Override
    public void validate(Object target, Errors errors) {
        
        Location location = (Location) target;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "Название не может быть пустым");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.empty", "Описание не может быть пустым");
        
        if (location.getName().equals("---")) {
            errors.rejectValue("name", "name.meaningless", "Введите осмысленное название");
        }
        
        if (location.getDescription().equals("---")) {
            errors.rejectValue("description", "description.meaningless", "Введите осмысленное описание");
        }
        
        logger.debug(errors.hasErrors());
    }   // end public void validate(Object target, Errors errors) {}
}
