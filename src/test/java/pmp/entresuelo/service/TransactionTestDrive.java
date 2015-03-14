/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmp.entresuelo.service;

/**
 *
 * @author Pozitron
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pmp.entresuelo.core.Category;
import pmp.entresuelo.core.CategoryDetails;
import pmp.entresuelo.core.Item;
import pmp.entresuelo.service.impl.CategoryDetailsManager;

public class TransactionTestDrive {
    
    public static void main(String... args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-database.xml");
        AbstractManager cdm = ctx.getBean("categoryDetailsManager", AbstractManager.class);        
        AbstractManager im = ctx.getBean("itemManager", AbstractManager.class);   
        AbstractManager cm = ctx.getBean("categoryManager", AbstractManager.class);   
        
        List <Category> categories = new ArrayList<Category>();
        categories.add((Category)cm.getEntityById(1));
        categories.add((Category)cm.getEntityById(2));
        
        CategoryDetails cd = new CategoryDetails((Item)im.getEntityById(1), categories);
        
        System.out.println(cdm.addNewEntity(cd));
        
        List<Category> newCategories = new ArrayList<Category>();
        newCategories.add((Category)cm.getEntityById(4));
        cd = new CategoryDetails((Item)im.getEntityById(1), newCategories);
        System.out.println(cdm.updateEntity(cd));
    }
    
}
