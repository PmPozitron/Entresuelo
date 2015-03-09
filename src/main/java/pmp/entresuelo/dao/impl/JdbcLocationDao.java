package pmp.entresuelo.dao.impl;

import java.util.List;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.SimpleLayout;

import pmp.entresuelo.dao.AbstractDao;
import pmp.entresuelo.core.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
//import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pmp.entresuelo.core.Item;
import pmp.entresuelo.dao.ResultSetProcessors.ItemMapper;
import pmp.entresuelo.dao.ResultSetProcessors.LocationMapper;


//@Component("locationDao")
public class JdbcLocationDao extends JdbcTemplate implements AbstractDao {
    private static final Logger logger = Logger.getLogger(JdbcLocationDao.class);
//    private static final ConsoleAppender consoleLog = new ConsoleAppender (new SimpleLayout(), ConsoleAppender.SYSTEM_OUT);
//	
//    private static void initLogger () {
//        JdbcLocationDao.logger.addAppender(JdbcLocationDao.consoleLog);
//	JdbcLocationDao.logger.setLevel(Level.ALL);
//		
//        JdbcLocationDao.logger.debug(new Date() + " private static void initLogger () {}");
//    }	// end private static void initLogger () {}
//	
//    static {
//	JdbcLocationDao.initLogger();
//    }	// end static
	
    public JdbcLocationDao () {
        super();
//	JdbcLocationDao.initLogger();
	JdbcLocationDao.logger.debug(new Date() + " testing updated message public JdbcLocationDao () {}");		
    }	// end public JdbcLocationDao () {}

//    @Autowired
//    DataSource dataSource;
        
    @Override
    public List <Location> getEntityByName(String name) {
        JdbcLocationDao.logger.debug(new Date() + " public Location getEntityByName(String name) {}");
		
	List <Location> locations = super.query(AbstractDao.SELECT_FROM_LOCATIONS + " WHERE name = '" + "name'", 
                new LocationMapper());
		
        return locations;
    }	// end public Location getEntityByName(String name) {}
	
    @Override
    public List<Location> getAllEntities() {
	JdbcLocationDao.logger.debug(new Date() + " public List<Location> getAllEntities() {}");
		
	List <Location> locations = super.query(AbstractDao.SELECT_FROM_LOCATIONS, new LocationMapper());
	return locations;	
    }	// end public List<?> getAllEntities() {}

    @Override
    public <T> int addNewEntity(T newEntity) {
	JdbcLocationDao.logger.debug(new Date() + " public <T> int addNewEntity(T newEntity) {}");
		
	Location location = (Location)newEntity;        
        return super.update(AbstractDao.INSERT_INTO_LOCATIONS + "('" + location.getName() + "', '" + location.getDescription() + "');");        
    }	// end public <T> int addNewEntity(T newEntity) {} 

    @Deprecated // there's some bug in spring 3.2.8 jdbc for which sqlparams binding isn't working. or may be it is some mine bug
    @Override
    public <T> void updateEntity(int idToUpdate, T updatedEntity) {
        JdbcLocationDao.logger.debug(new Date() + " public <T> void updateEntity(int idToUpdate, T updatedEntity) {}");
		
	Location location = (Location)updatedEntity;
	int count = super.update("UPDATE entresuelo.location SET name = :name, desrciption = :description WHERE id = :id", 
                new LocationMapper(), new MapSqlParameterSource().addValue("name", location.getName())
                        .addValue("description", location.getDescription()).addValue("id", location.getId()));				
    }	// end public <T> void updateEntity(int idToUpdate, T updatedEntity) {}

    @Override
    public int deleteEntity(int idToDelete) {
	JdbcLocationDao.logger.debug(new Date() + " public void deleteEntity(int idToDelete) {}");
		
	return super.update(AbstractDao.DELETE_LOCATION + " WHERE id = " + idToDelete);       
    }	// end public void deleteEntity(int idToDelete) {}

    @Override
    public Location getEntityById(int id) {
        JdbcLocationDao.logger.debug(new Date() + " public Item getEntityById(int id) {}");
        List<Location> locations = super.query(AbstractDao.SELECT_FROM_LOCATIONS + " WHERE l.id = " + id + ";", new LocationMapper());

        if (locations.size() > 1) {
            throw new IllegalStateException(new Date() + " " + this.getClass().getName() + " more than one result for 'byId' query");
        }
        
        if (locations.size() == 0) {
            return new Location();
        }
        
        return locations.get(0);
    }   // end public Item getEntityById(int id) {}

    @Override
    public <T> int updateEntity(T updatedEntity) {
        Location location = (Location)updatedEntity;
        
        return super.update(AbstractDao.UPDATE_LOCATION + " name = '" + location.getName() 
                + "', description = '" + location.getDescription() + "' WHERE id = " + location.getId());
    }
}   // end public class LocationDao extends SimpleJdbcDaoSupport implements AbstractDao {}
