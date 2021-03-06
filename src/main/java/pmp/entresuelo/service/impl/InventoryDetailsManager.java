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

    @Autowired
    private AbstractDao inventoryDetailsDao;
    @Autowired
    private AbstractDao itemDao;

    public InventoryDetailsManager() {
//	InventoryDetailsManager.initLogger();
        InventoryDetailsManager.logger.debug(new Date() + " testing new message public InventoryDetailsManager () {}");
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
//        List<InventoryDetails> inventoryDetails = this.inventoryDetailsDao.getAllEntities();

        return getAllInventories();
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
        if (((InventoryDetails) newEntity).getContainer() == null) {
            return 0;
        }
        return this.inventoryDetailsDao.addNewEntity(newEntity);
    }

    @Override
    public <T> T getNewEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public InventoryDetails getNewEntity(SimpleItemAdder itemAdder) {

        InventoryDetails id = new InventoryDetails();
//        id.setContainer((Item)this.itemDao.getEntityById(itemAdder.getContainerId()));
        id.setContainer((itemAdder.getContainerId() == -1 || itemAdder.getContainerId() == 0)
                ? null : ((Item) this.itemDao.getEntityById(itemAdder.getContainerId()))
        );

        List<Item> inventory = new ArrayList<Item>();
        id.setInventory(inventory);
        id.getInventory().add(itemAdder.getItem());

        return id;
    }

    @Override
    public InventoryDetails getEntityById(int id) {
//        return this.inventoryDetailsDao.getEntityById(id);
        return getInventoryDetailsByContainer((Item) itemDao.getEntityById(id));
    }

    public InventoryDetails getInventoryDetailsByContainer(Item container) {
        return ((JdbcInventoryDetailsDao) this.inventoryDetailsDao).getInventoryDetailsByContainer(container);
    }

    public Item getContainerByItem(Item item) {
        return ((JdbcInventoryDetailsDao) this.inventoryDetailsDao).getContainerByItem(item);
    }

    @Override
    public <T> int updateEntity(T entity) {
        InventoryDetails newDetails = (InventoryDetails) entity;
        int oldContainerId = getContainerByItemId(newDetails.getInventory().get(0).getId()).getId();
        int newContainerId = -1;
        
        if (newDetails.getContainer() == null || newDetails.getContainer().getId() == 0 || newDetails.getContainer().getId() == -1) {
            
        } else {
            newContainerId = newDetails.getContainer().getId();
        }
        return updateContainerForItem(oldContainerId, newContainerId, newDetails.getInventory().get(0).getId());
    }

    public int updateContainerForItem(int oldContainerId, int newContainerId, int itemId) {
        return ((JdbcInventoryDetailsDao) inventoryDetailsDao).updateContainerForItem(oldContainerId, newContainerId, itemId);
    }

    public int deleteInventoryDetailsForItem(Item item) {
        return ((JdbcInventoryDetailsDao) inventoryDetailsDao).deleteInventoryDetailsForItem(item);
    }

    @Override
    public List<Item> getAllContainers() {
        return ((JdbcInventoryDetailsDao) inventoryDetailsDao).getAllContainers();
    }

    @Override
    public List<?> getAllEntities(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteEntityById(int id) {
        return deleteInventoryDetailsForItem((Item) itemDao.getEntityById(id));
    }

    @Override
    public InventoryDetails getNewEntityByDetails(SimpleItemAdder adder) {
        return getNewEntity(adder);
    }

    @Override
    public Item getContainerByItemId(int id) {
        Item item = (Item) itemDao.getEntityById(id);

        return getContainerByItem(item);
    }
}	// end public class InventoryDetailsManager implements AbstractManager {}
