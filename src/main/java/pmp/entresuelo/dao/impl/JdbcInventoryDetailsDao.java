package pmp.entresuelo.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.SimpleLayout;

import pmp.entresuelo.dao.AbstractDao;
import pmp.entresuelo.core.Category;
import pmp.entresuelo.core.InventoryDetails;
import pmp.entresuelo.core.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import pmp.entresuelo.core.CategoryDetails;
import pmp.entresuelo.dao.ResultSetProcessors.InventoryDetailsExtractor;
import pmp.entresuelo.dao.ResultSetProcessors.ItemMapper;

//@Component("inventoryDetailsDao")
public class JdbcInventoryDetailsDao extends JdbcTemplate implements AbstractDao {

    private static final Logger logger = Logger.getLogger(JdbcInventoryDetailsDao.class);
    private static final ConsoleAppender consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_OUT);

    private static void initLogger() {
        JdbcInventoryDetailsDao.logger.addAppender(JdbcInventoryDetailsDao.consoleLog);
        JdbcInventoryDetailsDao.logger.setLevel(Level.ALL);

        JdbcInventoryDetailsDao.logger.debug(new Date() + " private static void initLogger () {}");
    }	// end private static void initLogger () {}

    static {
        JdbcInventoryDetailsDao.initLogger();
    }	// end static

//    @Autowired
//    DataSource dataSource;
    private static final String SELECT_INVENTORY_DETAILS
            = "SELECT * "
            + "FROM entresuelo.inventory_details AS id "
            + "INNER JOIN entresuelo.item AS ic "
            + "ON id.item_holder_id = ic.id "
            + "INNER JOIN entresuelo.location AS l "
            + "ON ic.location_id = l.id "
            + "INNER JOIN entresuelo.item AS ii "
            + "ON ii.id = id.stored_item_id ";

    public JdbcInventoryDetailsDao() {
        super();
        JdbcInventoryDetailsDao.logger.debug(new Date() + " public JdbcInventoryDetailsDao () {}");
    }	// end public JdbcInventoryDetailsDao () {}

//  так как InventoryDetails - это характеристика Item, то данный метод требует на вход имя Item, для которого нужно найти данные.
//  возвращать метод должен набор айтемов, принадлежащих контейнеру с itemName.   
//  07/12/14: есть ощущение, что исп-ие класса InventoryDetails тут не слишком нужно;
//  возможно, будет проще возвращать лист айтемов, принадлежащих контейнеру, на уровень службы
//  и уже на уровне службы ассоциировать контейнер с его инвентарем.
//  эта новая парадигма будет реализована м-дом getInventoryByContainerName(String container)    
    public List<InventoryDetails> getEntityByName(String itemName) {
        List<InventoryDetails> inventoryDetails = (List<InventoryDetails>) super.query(
                //                JdbcInventoryDetailsDao.SELECT_INVENTORY_DETAILS + " WHERE ic.name = '" + itemName + "';", 
                AbstractDao.SELECT_FROM_INVENTORY_DETAILS + " WHERE i.name = + '" + itemName + "';",
                new InventoryDetailsExtractor());

        return inventoryDetails;
    }   // end public List <InventoryDetails> getEntityByName(String itemName) {}

//  07/12/2014: метод представляется бессмысленным в контексте работы с инвентарем контейнера (НЕ ЛОКАЦИИ) 
//  хотя, возможно, есть смысл его использовать для разового процессинга всех контейнеров    
    @Override
    public List<InventoryDetails> getAllEntities() {
        JdbcInventoryDetailsDao.logger.debug(new Date() + " public List<InventoryDetails> getAllEntities() {}");

        List<InventoryDetails> inventories = (List<InventoryDetails>) super.query(JdbcInventoryDetailsDao.SELECT_FROM_INVENTORY_DETAILS, new InventoryDetailsExtractor());

        return inventories;
    }	// end public List<?> getAllEntities() {}

    @Override
    public <T> int addNewEntity(T newEntity) {
        JdbcInventoryDetailsDao.logger.debug(new Date() + " public <T> int addNewEntity(T newEntity) {}");

        int count = 0;
        for (Item item : ((InventoryDetails)newEntity).getInventory()) {
            super.update(AbstractDao.INSERT_INTO_INVENTORY_DETAILS + "(" + ((InventoryDetails)newEntity).getContainer().getId() + "," + item.getId() + ")");
            count ++;
        }
        return count;      
    }	// end public <T> int addNewEntity(T newEntity) {} 

    @Override
    public <T> void updateEntity(int idToUpdate, T updatedEntity) {
        JdbcInventoryDetailsDao.logger.debug(new Date() + " public <T> void updateEntity(int idToUpdate, T updatedEntity) {}");

        throw new UnsupportedOperationException();
    }	// end public <T> void updateEntity(int idToUpdate, T updatedEntity) {}

    @Override
    public void deleteEntity(int idToDelete) {
        JdbcInventoryDetailsDao.logger.debug(new Date() + " public void deleteEntity(int idToDelete) {}");

        throw new UnsupportedOperationException();
    }	// end public void deleteEntity(int idToDelete) {}

//  07/12/14: Добавляю этот метод ради ухода от исп-ия InventoryDetailsExtractor    
    public List<Item> getInventoryByContainerName(String containerName) {
        JdbcInventoryDetailsDao.logger.debug(new Date() + " public List <Item> getInventoryByContainerName(String containerName) {}");

        List<Item> inventory = (List<Item>) super.query(AbstractDao.SELECT_FROM_INVENTORY_DETAILS + " WHERE ih.name = '" + containerName + "';", new ItemMapper());

        return inventory;
    }   // end public List <Item> getInventoryByContainerName(String containerName) {}

//  11/01/15: it seems to be more suitable to return from this object not List<Item>, 
//  but InventoryDetails. after all this is InventoryDetailsDAO, not ItemDAO...
    public InventoryDetails getInventoryDetailsByContainer(Item container) {
        JdbcInventoryDetailsDao.logger.debug(new Date() + " public InventoryDetails getInventoryDetailsByContainerName(String containerName) {}");

        List<Item> items = this.getInventoryByContainerName(container.getName());
        InventoryDetails inventory = new InventoryDetails(container, items);

        return inventory;
    }   // end public InventoryDetails getInventoryDetailsByContainerName(String containerName) {}

    @Override
    public Item getEntityById(int id) {
        JdbcInventoryDetailsDao.logger.debug(new Date() + " public Item getEntityById(int id) {}");
//        List<Item> items = super.query(AbstractDao.SELECT_FROM_ITEMS + " WHERE i.id = " + id + ";", new ItemMapper());
//
//        if (items.size() > 1) {
//            throw new IllegalStateException(new Date() + " " + this.getClass().getName() + " more than one result for 'byId' query");
//        }
//        return items.get(0);
        throw new UnsupportedOperationException("JdbcInventoryDetailsDao.getEntityById() - don't see any sense in this method here, 28/02/2015");
    }   // end public Item getEntityById(int id) {}
}   // end public class JdbcInventoryDetailsDao extends JdbcTemplate implements AbstractDao {}
