package pmp.entresuelo.core;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class Item implements Serializable {
	private static final Logger logger = Logger.getLogger(Item.class);
	private static final ConsoleAppender consoleLog = new ConsoleAppender (new SimpleLayout(), ConsoleAppender.SYSTEM_ERR);
	
	private static void initLogger() {
		Item.logger.addAppender(Item.consoleLog);
		Item.logger.setLevel(Level.ALL);
		
		Item.logger.debug(new Date() + " private static void initLogger() {}");
		
	}	// end private static void initLogger() {}
	
	static {
		Item.initLogger();
	}	// end static
	
	private int id;
	private String name;
	private String description;
	private int locationId;
	private String location;
	
	public Item () {
		super();
//		Item.initLogger();
		Item.logger.debug(new Date() + " public Item (){}");		
	}	// end public Location () {}
	
	public Item (int id, String name, String description, int locationId, String location) {
		super();
//		Item.initLogger();
		Item.logger.debug(new Date() + " public Item (int id, String name, String description, int locationId, String location) {}");	
		
		this.id = id;
		this.name = name;
		this.description = description;
		this.locationId = locationId;
		this.location = location;
	}	// end public Item (int id, String name, String description, String location) {}
	
	public void setId (int id) {
//		Item.logger.debug(new Date() + " public void setId (int id) {}");
		
		this.id = id;		
	}	// end public void setId (int id) {}
	
	public int getId () {
//		Item.logger.debug(new Date() + " public int getId () {}");
		
		return this.id;
	}	// end public int getId () {}
	
	public void setName (String name) {
//		Item.logger.debug(new Date() + " public void setName (String name) {}");
		
		this.name = name;
	}	// end public void setName (String name) {}
	
	public String getName () {
//		Item.logger.debug(new Date() + " public String getName () {}");
		
		return this.name;
	}	// end public String getName () {}
	
	public void setDescription (String description) {
//		Item.logger.debug(new Date() + " public void setDescription (String description) {");
		
		this.description = description;
	}	// end public void setDescription (String description) {}
	
	public String getDescription() {
//		Item.logger.debug(new Date() + " public String getDescription() {}");
		
		return this.description;
	}	// end public String getDescription() {}
	
	public void setLocationId(int locationId) {
//		Item.logger.debug(new Date () + " public void setLocationId(int locationId) {}");
		
		this.locationId = locationId;
	}	// end public void setLocationId(int locationId) {}
	
	public int getLocationId() {
//		Item.logger.debug(new Date() + " public int getLocationId() {}");
		
		return this.locationId;
	}	// end public int getLocationId() {}
	
	public void setLocation(String location) {
//		Item.logger.debug(new Date() + " public String setLocation() {}");
		
		this.location = location;
	}	// end public void setLocation(String location) {}
	
	public String getLocation(){
//		Item.logger.debug(new Date() + " public int getLocationId(){}");
		
		return this.location;
	}	// end public String getLocation(){}
}	// end public class Location implements Serializable {}
