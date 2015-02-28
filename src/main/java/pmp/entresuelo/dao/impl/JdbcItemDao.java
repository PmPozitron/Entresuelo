package pmp.entresuelo.dao.impl;

import java.util.List;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.SimpleLayout;

import pmp.entresuelo.dao.AbstractDao;
import pmp.entresuelo.core.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
//import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pmp.entresuelo.dao.ResultSetProcessors.ItemMapper;

//@Component("itemDao")
public class JdbcItemDao extends JdbcTemplate implements AbstractDao {

    private static final Logger logger = Logger.getLogger(JdbcItemDao.class);
    private static final ConsoleAppender consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_OUT);

    private static void initLogger() {
        JdbcItemDao.logger.addAppender(JdbcItemDao.consoleLog);
        JdbcItemDao.logger.setLevel(Level.ALL);

        JdbcItemDao.logger.debug(new Date() + " private static void initLogger () {}");
    }	// end private static void initLogger () {}

    static {
        JdbcItemDao.initLogger();
    }	// end static

//    private static final String SELECT_ALL_ITEMS =
//              "SELECT i.id, i.name, i.description, l.id, l.name "
//            + "FROM entresuelo.item AS i "
//            + "INNER JOIN entresuelo.location AS l "
//            + "ON i.location_id = l.id";
//    @Autowired
//    DataSource dataSource;
    public JdbcItemDao() {
        super();
        JdbcItemDao.logger.debug(new Date() + " public JdbcItemDao () {}");
    }	// end public JdbcLocationDao () {}

    @Override
    public List<Item> getEntityByName(String name) {
        JdbcItemDao.logger.debug(new Date() + " public Item getEntityByName(String name) {}");
        List<Item> items = super.query(AbstractDao.SELECT_FROM_ITEMS + " WHERE i.name = '" + name + "';", new ItemMapper());

        return items;
    }	// end public Location getEntityByName(String name) {}

    @Override
    public List<Item> getAllEntities() {
        JdbcItemDao.logger.debug(new Date() + " public List<Item> getAllEntities() {}");
        List<Item> items = super.query(AbstractDao.SELECT_FROM_ITEMS, new ItemMapper());

        return items;
    }	// end public List<?> getAllEntities() {}

    @Override
    public <T> int addNewEntity(T newEntity) {
        JdbcItemDao.logger.debug(new Date() + " public <T> int addNewEntity(T newEntity) {}");
        Item item = (Item) newEntity;
//	int count = super.update("INSERT INTO entresuelo.item (name, description) VALUES (:name, :description", 
//                new ItemMapper(), new MapSqlParameterSource().addValue("name", item.getName())
//                        .addValue("description", item.getDescription()));

        int count = super.update(AbstractDao.INSERT_INTO_ITEMS + "('" + item.getName() + "', '" + item.getDescription() + "', "
                + item.getLocationId() + ")");

        List<Item> items = this.getEntityByName(item.getName());    // ToDo: comprehend if this query is really needed here

        return items.get(0).getId();
    }	// end public <T> int addNewEntity(T newEntity) {} 

    @Override
    public <T> void updateEntity(int idToUpdate, T updatedEntity) {
        JdbcItemDao.logger.debug(new Date() + " public <T> void updateEntity(int idToUpdate, T updatedEntity) {}");
        Item Item = (Item) updatedEntity;
        int count = super.update("UPDATE entresuelo.item SET name = :name, desrciption = :description WHERE id = :id",
                new ItemMapper(), new MapSqlParameterSource().addValue("name", Item.getName())
                .addValue("description", Item.getDescription()).addValue("id", Item.getId()));
    }	// end public <T> void updateEntity(int idToUpdate, T updatedEntity) {}

    @Override
    public void deleteEntity(int idToDelete) {
        JdbcItemDao.logger.debug(new Date() + " public void deleteEntity(int idToDelete) {}");
        super.update("DELETE FROM entresuelo.item WHERE id = :id",
                new ItemMapper(), new MapSqlParameterSource().addValue("id", idToDelete));
    }	// end public void deleteEntity(int idToDelete) {}

    @Override
    public Item getEntityById(int id) {
        JdbcItemDao.logger.debug(new Date() + " public Item getEntityById(int id) {}");
        List<Item> items = super.query(AbstractDao.SELECT_FROM_ITEMS + " WHERE i.id = " + id + ";", new ItemMapper());

        if (items.size() > 1) {
            throw new IllegalStateException(new Date() + " " + this.getClass().getName() + " more than one result for 'byId' query");
        }
        return items.get(0);
    }   // end public Item getEntityById(int id) {}
}   // end public class LocationDao extends SimpleJdbcDaoSupport implements AbstractDao {}
