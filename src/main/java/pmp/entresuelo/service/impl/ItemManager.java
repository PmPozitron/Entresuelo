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

//@Component("itemManager")
public class ItemManager implements AbstractManager {

    private static final Logger logger = Logger.getLogger(ItemManager.class);

    @Autowired
    private AbstractDao itemDao;
        
    public ItemManager() {
        ItemManager.logger.debug(new Date() + " testing new message public ItemManager () {}");
    }	// end public ItemManager () {}

    public void setItemDao(AbstractDao dao) {
        ItemManager.logger.debug(new Date() + " public void setDao(AbstractDao dao) {}");

        this.itemDao = dao;
    }	// end public void setManager(AbstractManager manager) {}

    @Override
    public List<Item> getAllEntities() {
        List<Item> items = this.itemDao.getAllEntities();

        return items;
    }	// end public List<Item> getAllEntities() {}

    @Override
    public <T> int addNewEntity(T newEntity) {
        return this.itemDao.addNewEntity(newEntity);
    }   // end public <T> int addNewEntity(T newEntity) {}

    @Override
    public <T> T getNewEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T getEntityById(int id) {
        return this.itemDao.getEntityById(id);
    }

    @Override
    public <T> int updateEntity(T entity) {
        return this.itemDao.updateEntity(entity);
    }
    
    public int deleteItem(Item item) {
        return this.itemDao.deleteEntity(item.getId());
    }

    @Override
    public List<?> getAllEntities(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteEntityById(int id) {
        return itemDao.deleteEntity(id);        
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
    
    

}	// end public class ItemManager implements AbstractManager {}
