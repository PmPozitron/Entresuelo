package pmp.entresuelo.dao.impl;

import java.util.List;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.SimpleLayout;

import pmp.entresuelo.dao.AbstractDao;
import pmp.entresuelo.core.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
//import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pmp.entresuelo.dao.ResultSetProcessors.CategoryMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import pmp.entresuelo.core.Item;
import pmp.entresuelo.dao.ResultSetProcessors.ItemMapper;

//@Component("categoryDao")
public class JdbcCategoryDao extends JdbcTemplate implements AbstractDao {

    private static final Logger logger = Logger.getLogger(JdbcCategoryDao.class);
    private static final ConsoleAppender consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_OUT);

    private static void initLogger() {
        JdbcCategoryDao.logger.addAppender(JdbcCategoryDao.consoleLog);
        JdbcCategoryDao.logger.setLevel(Level.ALL);

        JdbcCategoryDao.logger.debug(new Date() + " private static void initLogger () {}");
    }	// end private static void initLogger () {}

    static {
        JdbcCategoryDao.initLogger();
    }	// end static
    
//    @Autowired
//    private DataSource dataSource;
//    
//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    public JdbcCategoryDao() {
        super();
//	JdbcCategoryDao.initLogger();
        JdbcCategoryDao.logger.debug(new Date() + " public JdbcCategoryDao () {}");
    }	// end public JdbcCategoryDao () {}

    @Override
    public List<Category> getEntityByName(String name) {
        JdbcCategoryDao.logger.debug(new Date() + " public Category getEntityByName(String name) {}");

        List<Category> categories = super.query(AbstractDao.SELECT_FROM_CATEGORIES + " WHERE name = '" + name + "';",
                new CategoryMapper(), new MapSqlParameterSource().addValue("name", name));

        return categories;
    }	// end public List <Category> getEntityByName(String name) {}

    @Override
    public List<Category> getAllEntities() {
        JdbcCategoryDao.logger.debug(new Date() + " public List<Category> getAllEntities() {}");

        List<Category> categories = super.query(AbstractDao.SELECT_FROM_CATEGORIES, new CategoryMapper());

        return categories;
    }	// end public List<?> getAllEntities() {}

    @Override
    public <T> int addNewEntity(T newEntity) {
        JdbcCategoryDao.logger.debug(new Date() + " public <T> int addNewEntity(T newEntity) {}");

        Category category = (Category) newEntity;
        return super.update(AbstractDao.INSERT_INTO_CATEGORIES + "('" + category.getName() + "', '" + category.getDescription() + "');");
    }	// end public <T> int addNewEntity(T newEntity) {} 

    @Override
    public <T> void updateEntity(int idToUpdate, T updatedEntity) {
        JdbcCategoryDao.logger.debug(new Date() + " public <T> void updateEntity(int idToUpdate, T updatedEntity) {}");

        Category category = (Category) updatedEntity;
        int count = super.update("UPDATE entresuelo.category SET name = :name, desrciption = :description WHERE id = :id",
                new CategoryMapper(), new MapSqlParameterSource().addValue("name", category.getName())
                .addValue("description", category.getDescription()).addValue("id", category.getId()));

    }   // end public <T> void updateEntity(int idToUpdate, T updatedEntity) {}

    @Override
    public void deleteEntity(int idToDelete) {
        JdbcCategoryDao.logger.debug(new Date() + " public void deleteEntity(int idToDelete) {}");

        super.update("DELETE FROM entresuelo.category WHERE id = :id",
                new CategoryMapper(), new MapSqlParameterSource().addValue("id", idToDelete));
    }	// end public void deleteEntity(int idToDelete) {}

    @Override
    public Category getEntityById(int id) {
        JdbcCategoryDao.logger.debug(new Date() + " public Item getEntityById(int id) {}");
        List<Category> categories = super.query(AbstractDao.SELECT_FROM_CATEGORIES + " WHERE c.id = " + id + ";", new CategoryMapper());

        if (categories.size() > 1) {
            throw new IllegalStateException(new Date() + " " + this.getClass().getName() + " more than one result for 'byId' query");
        }
        return categories.get(0);
    }   // end public Item getEntityById(int id) {}
}   // end public class CategoryDao extends SimpleJdbcDaoSupport implements AbstractDao {}
