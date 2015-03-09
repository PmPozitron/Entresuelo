package pmp.entresuelo.service.impl;

import java.util.ArrayList;
import pmp.entresuelo.service.AbstractManager;
import pmp.entresuelo.core.*;
import pmp.entresuelo.dao.AbstractDao;
import pmp.entresuelo.dao.impl.JdbcItemDao;
import pmp.entresuelo.dao.impl.JdbcLocationDao;

import java.util.List;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.SimpleLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pmp.entresuelo.dao.impl.JdbcCategoryDetailsDao;

//@Component("categoryDetailsManager")
//@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class CategoryDetailsManager implements AbstractManager {

    private static final Logger logger = Logger.getLogger(CategoryDetailsManager.class);
//    private static final ConsoleAppender consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_OUT);
//
//    private static void initLogger() {
//        CategoryDetailsManager.logger.addAppender(CategoryDetailsManager.consoleLog);
//        CategoryDetailsManager.logger.setLevel(Level.ALL);
//        CategoryDetailsManager.logger.debug(new Date() + " private static void initLogger() {}");
//    }	// end private static void initLogger() {}
//
//    static {
//        CategoryDetailsManager.initLogger();
//    }	// end static

    @Autowired
    private AbstractDao categoryDetailsDao;
    @Autowired
    private AbstractDao categoryDao;
    @Autowired
    private AbstractDao itemDao;

    public CategoryDetailsManager() {
//		CategoryDetailsManager.initLogger();
        CategoryDetailsManager.logger.debug(new Date() + " testing new message public CategoryDetailsManager () {}");
    }	// end public CategoryDetailsManager () {}

    public void setCategoryDetailsDao(AbstractDao dao) {
        CategoryDetailsManager.logger.debug(new Date() + " public void setDao(AbstractDao dao) {}");

        this.categoryDetailsDao = dao;
    }	// end public void setManager(AbstractManager manager) {}

    public void setCategoryDao(AbstractDao dao) {
        CategoryDetailsManager.logger.debug(new Date() + " public void setCategorydao(AbstractDao dao) {}");

        this.categoryDao = dao;
    }   // end public void setCategorydao(AbstractDao dao) {}

    public void setItemDao(AbstractDao dao) {
        CategoryDetailsManager.logger.debug(new Date() + " public void setItemDao(AbstractDao dao) {}");

        this.itemDao = dao;
    }   // end public void setItemDao(AbstractDao dao) {}

    @Override
    public List<CategoryDetails> getAllEntities() {
        List<CategoryDetails> categoryDetails = this.categoryDetailsDao.getAllEntities();

        return categoryDetails;
    }	// end public List<Item> getAllEntities() {}

    public List<CategoryDetails> getAllCategoryDetails() {
        List<CategoryDetails> categoryDetails = new ArrayList<CategoryDetails>();
        List<Item> items = this.itemDao.getAllEntities();

        for (Item item : items) {
            CategoryDetails categoryDetail = ((JdbcCategoryDetailsDao) categoryDetailsDao).getCategoryDetailsByItem(item);
            categoryDetails.add(categoryDetail);
        }   // end for

        return categoryDetails;
    }   // end public List<CategoryDetails> getAllCategoryDetails() {}

    @Override
    public <T> int addNewEntity(T newEntity) {
        return this.categoryDetailsDao.addNewEntity(newEntity);
    }

    @Override
    public CategoryDetails getNewEntity() {
        return new CategoryDetails();
    }

    public CategoryDetails getNewEntity(SimpleItemAdder itemAdder) {

        CategoryDetails cd = new CategoryDetails();
        cd.setItem(itemAdder.getItem());
        List<Category> categories = new ArrayList<Category>();
        cd.setCategories(categories);

        if (itemAdder.getCategories() != null) {
            for (int id : itemAdder.getCategories()) {
                cd.getCategories().add((Category) this.categoryDao.getEntityById(id));
            }
        }

        return cd;
    }

    @Override
    public <T> T getEntityById(int id) {
        return this.categoryDetailsDao.getEntityById(id);
    }

    public CategoryDetails getCategoryDetailsByItem(Item item) {
        return ((JdbcCategoryDetailsDao) this.categoryDetailsDao).getCategoryDetailsByItem(item);
    }

    public List<Category> getCategoriesByItem(String itemName) {
        return ((JdbcCategoryDetailsDao) this.categoryDetailsDao).getCategoriesByItemName(itemName);
    }

    @Override
    public <T> int updateEntity(T entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int updateCategoriesForItem(List<Category> newCategories, CategoryDetails cd) {
        if (cd != null && cd.getCategories() != null) {
            ((JdbcCategoryDetailsDao)(categoryDetailsDao)).deleteCategoriesForItem(cd.getItem());
        }
        
        if (newCategories != null && newCategories.isEmpty()) {
            return ((JdbcCategoryDetailsDao)(categoryDetailsDao)).insertCategoryDetailsForItem(newCategories, cd.getItem());
        }
        
        return -1;
    }
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int updateCategoriesForItem(int[] newCategoryIds, CategoryDetails cd) {
        if (cd.getCategories() != null) {
            ((JdbcCategoryDetailsDao)(categoryDetailsDao)).deleteCategoriesForItem(cd.getItem());
        }
        
        if (newCategoryIds != null && newCategoryIds.length != 0) {
            return ((JdbcCategoryDetailsDao)(categoryDetailsDao)).insertCategoryDetailsForItem(newCategoryIds, cd.getItem());
        }
        
        return -1;
    }
    
    public int deleteCategoriesForItem(CategoryDetails cd) {
        return this.updateCategoriesForItem(new ArrayList<Category>(), cd);
    }
}   // end public class CategoryDetailsManager implements AbstractManager {}
