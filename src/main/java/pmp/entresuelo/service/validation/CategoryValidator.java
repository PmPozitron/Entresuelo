/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmp.entresuelo.service.validation;

import java.util.Date;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pmp.entresuelo.core.Category;

/**
 *
 * @author Pozitron
 */
@Component
public class CategoryValidator implements Validator {

    private static Logger logger = Logger.getLogger(CategoryValidator.class);
//    private static ConsoleAppender consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_ERR);
//
//    private static void initLogger() {
//        CategoryValidator.logger.addAppender(CategoryValidator.consoleLog);
//        CategoryValidator.logger.setLevel(Level.ALL);
//
//        CategoryValidator.logger.debug(new Date() + " CategoryValidator.initLogger() {}");
//    }   // end private static void initLogger() {}
//
//    static {
//        CategoryValidator.initLogger();
//    }   // end static

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Category.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Category category = (Category) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "Название не может быть пустым");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.empty", "Описание не может быть пустым");

        if (category.getName().equals("---")) {
            errors.rejectValue("name", "name.meaningless", "Введите осмысленное название");
        }

        if (category.getDescription().equals("---")) {
            errors.rejectValue("description", "description.meaningless", "Введите осмысленное описание");
        }

        logger.debug(errors.hasErrors());
    }

}   // end public class CategoryValidator implements Validator {}
