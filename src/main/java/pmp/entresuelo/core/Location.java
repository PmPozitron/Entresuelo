package pmp.entresuelo.core;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class Location implements Serializable {

    private static final Logger logger = Logger.getLogger(Location.class);
    private static final ConsoleAppender consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_ERR);

    private static void initLogger() {
        Location.logger.addAppender(Location.consoleLog);
        Location.logger.setLevel(Level.ALL);

        Location.logger.debug(new Date() + " private static void initLogger() {}");
    }	// end private static void initLogger() {}

    static {
        Location.initLogger();
    }	// end static

    private int id;
    private String name;
    private String description;

    public Location() {
//	Location.initLogger();
        Location.logger.debug(new Date() + " public Location (){}");
    }	// end public Location () {}

    public Location(int id, String name, String description) {
//	Location.initLogger();
        Location.logger.debug(new Date() + " public Location (int id, String name, String description) {}");

        this.id = id;
        this.name = name;
        this.description = description;
    }	// end public Location (int id, String name, String description) {}

    public void setId(int id) {
//	Location.logger.debug(new Date() + " public void setId (int id) {}");

        this.id = id;
    }	// end public void setId (int id) {}

    public int getId() {
//		Location.logger.debug(new Date() + " public int getId () {}");

        return this.id;
    }	// end public int getId () {}

    public void setName(String name) {
//	Location.logger.debug(new Date() + " public void setName (String name) {}");

        this.name = name;
    }	// end public void setName (String name) {}

    public String getName() {
//	Location.logger.debug(new Date() + " public String getName () {}");

        return this.name;
    }	// end public String getName () {}

    public void setDescription(String description) {
//	Location.logger.debug(new Date() + " public void setDescription (String description) {");

        this.description = description;
    }	// end public void setDescription (String description) {}

    public String getDescription() {
//	Location.logger.debug(new Date() + " public String getDescription() {}");

        return this.description;
    }	// end public String getDescription() {}
}   // end public class Location implements Serializable {}
