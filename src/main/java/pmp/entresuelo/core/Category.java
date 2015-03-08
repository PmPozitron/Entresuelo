package pmp.entresuelo.core;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class Category implements Serializable {

    private static final Logger logger = Logger.getLogger(Category.class);
//    private static final ConsoleAppender consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_ERR);

//    private static void initLogger() {
//        Category.logger.addAppender(Category.consoleLog);
//        Category.logger.setLevel(Level.ALL);
//
//        Category.logger.debug(new Date() + " private static void initLogger() {}");
//    }	// end private static void initLogger() {}

//    static {
//        Category.initLogger();
//    }	// end static

    private int id;
    private String name;
    private String description;

    public Category() {
//		Category.initLogger();
        Category.logger.debug(new Date() + " public Category (){}");
    }	// end public Location () {}

    public Category(int id, String name, String description) {
//		Category.initLogger();
        Category.logger.debug(new Date() + " testing updated message public Category (int id, String name, String description) {}");

        this.id = id;
        this.name = name;
        this.description = description;
    }	// end public Location (int id, String name, String description) {}

    public void setId(int id) {
//		Category.logger.debug(new Date() + " public void setId (int id) {}");

        this.id = id;
    }	// end public void setId (int id) {}

    public int getId() {
//		Category.logger.debug(new Date() + " public int getId () {}");

        return this.id;
    }	// end public int getId () {}

    public void setName(String name) {
//	Category.logger.debug(new Date() + " public void setName (String name) {}");

        this.name = name;
    }	// end public void setName (String name) {}

    public String getName() {
//		Category.logger.debug(new Date() + " public String getName () {}");

        return this.name;
    }	// end public String getName () {}

    public void setDescription(String description) {
//	Category.logger.debug(new Date() + " public void setDescription (String description) {");

        this.description = description;
    }	// end public void setDescription (String description) {}

    public String getDescription() {
//	Category.logger.debug(new Date() + " public String getDescription() {}");

        return this.description;
    }	// end public String getDescription() {}
    
    @Override
    public String toString() {
        return this.getName();
    }
    
    @Override
    public boolean equals (Object o) {
        if (this == null && o == null) {
            return true;
        }
        
        if ((this == null && o != null) ||
                (this != null && o == null)) {
            return false;
        }
        
        Category cat = (Category) o;
        
        if (this.getId() != cat.getId() || 
                !this.getName().equals(cat.getName()) || 
                !this.getDescription().equals(cat.getDescription())) {
            return false;
        }
        
        return true;    
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 97 * hash + (this.description != null ? this.description.hashCode() : 0);
        return hash;
    }
}   // end public class Location implements Serializable {}
