package pmp.entresuelo.service.impl;

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
import pmp.entresuelo.dao.impl.JdbcCategoryDetailsDao;

//@Component("categoryManager")
public class CategoryManager implements AbstractManager {

    private static final Logger logger = Logger.getLogger(CategoryManager.class);
    private static final ConsoleAppender consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_OUT);

    private static void initLogger() {
        CategoryManager.logger.addAppender(CategoryManager.consoleLog);
        CategoryManager.logger.setLevel(Level.ALL);
        CategoryManager.logger.debug(new Date() + " private static void initLogger() {}");
    }	// end private static void initLogger() {}

    static {
        CategoryManager.initLogger();
    }	// end static

    @Autowired
    private AbstractDao categoryDao;

    public CategoryManager() {
//		CategoryManager.initLogger();
        CategoryManager.logger.debug(new Date() + " public CategoryManager () {}");
    }	// end public CategoryManager () {}

    public void setCategoryDao(AbstractDao dao) {
        CategoryManager.logger.debug(new Date() + " public void setDao(AbstractDao dao) {}");

        this.categoryDao = dao;
    }	// end public void setCategoryDao(AbstractDao dao) {}

    @Override
    public List<Category> getAllEntities() {
        List<Category> categories = this.categoryDao.getAllEntities();

        return categories;
    }	// end public List<Item> getAllEntities() {}

    @Override
    public <T> int addNewEntity(T newEntity) {
        return categoryDao.addNewEntity(newEntity);
    }   // end public <T> int addNewEntity(T newEntity) {}

    @Override
    public <T> T getNewEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category getEntityById(int id) {
        return categoryDao.getEntityById(id);
    }

}	// end public class CategoryManager implements AbstractManager {}
