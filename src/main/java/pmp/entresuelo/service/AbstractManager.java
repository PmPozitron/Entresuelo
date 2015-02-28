package pmp.entresuelo.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.SimpleLayout;

import pmp.entresuelo.service.impl.LocationManager;

public interface AbstractManager extends Serializable {
//	Logger logger = Logger.getLogger(AbstractManager.class);
//	ConsoleAppender consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_OUT);	
	
	List <?> getAllEntities();
        
        <T> T getEntityById(int id);
        
        <T> int addNewEntity(T newEntity);
        
        <T> T getNewEntity();

}	// end public interface AbstractManager extends Serializable {