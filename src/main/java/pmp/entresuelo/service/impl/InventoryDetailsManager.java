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
import pmp.entresuelo.dao.impl.JdbcInventoryDetailsDao;

//@Component("inventoryDetailsManager")
public class InventoryDetailsManager implements AbstractManager {

    private static final Logger logger = Logger.getLogger(InventoryDetailsManager.class);
    private static final ConsoleAppender consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_OUT);

    private static void initLogger() {
        InventoryDetailsManager.logger.addAppender(InventoryDetailsManager.consoleLog);
        InventoryDetailsManager.logger.setLevel(Level.ALL);
        InventoryDetailsManager.logger.debug(new Date() + " private static void initLogger() {}");
    }	// end private static void initLogger() {}

    static {
        InventoryDetailsManager.initLogger();
    }	// end static

    @Autowired
    private AbstractDao inventoryDetailsDao;
    @Autowired
    private AbstractDao itemDao;

    public InventoryDetailsManager() {
//	InventoryDetailsManager.initLogger();
        InventoryDetailsManager.logger.debug(new Date() + " public InventoryDetailsManager () {}");
    }	// end public InventoryDetailsManager () {}

    public void setInventoryDetailsDao(AbstractDao dao) {
        InventoryDetailsManager.logger.debug(new Date() + " public void setDao(AbstractDao dao) {}");

        this.inventoryDetailsDao = dao;
    }	// end public void setManager(AbstractManager manager) {}

    public void setItemDao(AbstractDao dao) {
        InventoryDetailsManager.logger.debug(new Date() + " public void setItemDao(AbstractDao dao) {}");

        this.itemDao = dao;
    }   // end public void setItemDao(AbstractDao dao) {}

    @Override
    public List<InventoryDetails> getAllEntities() {
        List<InventoryDetails> inventoryDetails = this.inventoryDetailsDao.getAllEntities();

        return inventoryDetails;
    }	// end public List<Item> getAllEntities() {}

    public List<InventoryDetails> getAllInventories() {
        List<Item> allItems = this.itemDao.getAllEntities();
        List<InventoryDetails> allInventoryDetails = new ArrayList<InventoryDetails>();

        for (Item item : allItems) {
            InventoryDetails inventoryDetails = ((JdbcInventoryDetailsDao) inventoryDetailsDao).getInventoryDetailsByContainer(item);

            if (inventoryDetails.getInventory().isEmpty()) {
                continue;
            }   // end if

            allInventoryDetails.add(inventoryDetails);
        }   // end for

        return allInventoryDetails;
    }   // end public List<InventoryDetails> getAllInventories() {}

    @Override
    public <T> int addNewEntity(T newEntity) {
        return this.inventoryDetailsDao.addNewEntity(newEntity);
    }

    @Override
    public <T> T getNewEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public InventoryDetails getNewEntity(SimpleItemAdder itemAdder) {

        InventoryDetails id = new InventoryDetails();
        id.setContainer((Item)this.itemDao.getEntityById(itemAdder.getContainerId()));
        List <Item> inventory = new ArrayList<Item>();
        id.setInventory(inventory);
        id.getInventory().add(itemAdder.getItem());

        return id;
    }

    @Override
    public <T> T getEntityById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}	// end public class InventoryDetailsManager implements AbstractManager {}
