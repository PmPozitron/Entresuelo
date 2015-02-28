package pmp.entresuelo.core;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import pmp.entresuelo.core.Location;

import junit.framework.TestCase;

public class LocationTest extends TestCase {

	private final static Logger logger = Logger.getLogger(LocationTest.class);
	private final static ConsoleAppender consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_OUT);
	
	private static void initLogger () {
		LocationTest.logger.addAppender(LocationTest.consoleLog);
		LocationTest.logger.setLevel(Level.ALL);
	}	// end private static void initLogger () {}
	
	static {
		LocationTest.initLogger();
	}	// end static
	
	private final static int LOCATION_ID = 777;
	private final static String LOCATION_NAME = "test_location_name";
	private final static String LOCATION_DESCRIPTION = "test_location_description";	
	private Location testLocation = null;
	
	public void testUnParameterizedConstructor () {
		assertNull (testLocation);
		
		this.testLocation = new Location();
		assertNotNull(this.testLocation);
		
		assertEquals("id should be 0 after instatiation with unparameterized constructor", this.testLocation.getId(), 0);
		assertNull(this.testLocation.getName());
		assertNull(this.testLocation.getDescription());
	}	// public void TestUnParameterizedConstructor () {}
	
}	// end public class LocationTest extends TestCase {}
