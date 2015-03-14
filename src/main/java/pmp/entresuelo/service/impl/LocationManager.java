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

//@Component("locationManager")
public class LocationManager implements AbstractManager {

    private static final Logger logger = Logger.getLogger(LocationManager.class);

    @Autowired
    private AbstractDao locationDao;

    public LocationManager() {
//		LocationManager.initLogger();
        LocationManager.logger.debug(new Date() + " public LocationManager () {}");
    }	// end public LocationManager () {}

    public void setLocationDao(AbstractDao dao) {
        LocationManager.logger.debug(new Date() + " testing new message public void setDao(AbstractDao dao) {}");

        this.locationDao = dao;
    }	// end public void setManager(AbstractManager manager) {}

    @Override
    public List<Location> getAllEntities() {
        List<Location> locations = this.locationDao.getAllEntities();

        return locations;
    }	// end public List<Item> getAllEntities() {}

    @Override
    public <T> int addNewEntity(T newEntity) {
        return this.locationDao.addNewEntity(newEntity);
    }

    @Override
    public <T> T getNewEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T getEntityById(int id) {
        return this.locationDao.getEntityById(id);
    }

    @Override
    public <T> int updateEntity(T entity) {
        return this.locationDao.updateEntity(entity);
    }

    @Override
    public int deleteEntityById(int locationId) {
        return this.locationDao.deleteEntity(locationId);
    }

    @Override
    public List<?> getAllEntities(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}	// end public class LocationManager implements AbstractManager {}
