package pmp.entresuelo.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.SimpleLayout;
import org.springframework.transaction.annotation.Transactional;
import pmp.entresuelo.core.CategoryDetails;
import pmp.entresuelo.core.Item;
import pmp.entresuelo.core.SimpleItemAdder;

import pmp.entresuelo.service.impl.LocationManager;

@Transactional
public interface AbstractManager extends Serializable {

    List<?> getAllEntities();
    
    List<?> getAllEntities(String filter);

    <T> T getEntityById(int id);

    <T> int addNewEntity(T newEntity);

    <T> T getNewEntity();
    
    <T> T getNewEntityByDetails(SimpleItemAdder adder);

    <T> int updateEntity(T entity);
    
    int deleteEntityById(int id);
    
//  ugly and (hopefully) temporary workaround for disabling the need of casting AbstractManager to InventoryDetailsManager.
//  such cast leads to classcast exception while using @Transactional    
    <T> List<T> getAllContainers();
    
    public Item getContainerByItemId(int id);

}   // end public interface AbstractManager extends Serializable {
