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

    @Autowired
    private AbstractDao categoryDao;

    public CategoryManager() {
//		CategoryManager.initLogger();
        CategoryManager.logger.debug(new Date() + " testing new message public CategoryManager () {}");
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

    @Override
    public <T> int updateEntity(T entity) {
        return this.categoryDao.updateEntity(entity);
    }
    
    public int deleteEntity(int idToDelete) {
        return this.categoryDao.deleteEntity(idToDelete);    }

    @Override
    public List<?> getAllEntities(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteEntityById(int id) {
        
        return categoryDao.deleteEntity(id);
    }

    @Override
    public <T> List<T> getAllContainers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CategoryDetails getNewEntityByDetails(SimpleItemAdder adder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item getContainerByItemId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}	// end public class CategoryManager implements AbstractManager {}
