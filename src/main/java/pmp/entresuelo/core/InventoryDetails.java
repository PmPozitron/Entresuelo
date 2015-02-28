package pmp.entresuelo.core;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.SimpleLayout;

public class InventoryDetails implements Serializable {

    private static final Logger logger = Logger.getLogger(InventoryDetails.class);
    private static final ConsoleAppender consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_OUT);

    private static void initLogger() {
        InventoryDetails.logger.addAppender(InventoryDetails.consoleLog);
        InventoryDetails.logger.setLevel(Level.ALL);

        InventoryDetails.logger.debug(new Date() + " private static void initLogger() {}");
    }	// end private static void initLogger() {}

    static {
        InventoryDetails.initLogger();
    }	// end static

//  private int itemHolderId;
//  private int [] storedItemsIds;
    private Item container;
    private List<Item> inventory;

    public InventoryDetails() {
        super();
//	InventoryDetails.initLogger();
        InventoryDetails.logger.debug(new Date() + " public InventoryDetails(){}");
    }	// end public InventoryDetails(){}

    public InventoryDetails(Item container, List<Item> inventory) {
        super();
//	InventoryDetails.initLogger();
        InventoryDetails.logger.debug(new Date() + " public InventoryDetails(int itemHolderId, int[] storedItemIds) {}");

        this.setContainer(container);
        this.setInventory(inventory);
    }	// end public InventoryDetails(int itemHolderId, int[] storedItemIds) {}

    public void setContainer(Item container) {
        this.container = container;
    }	// end public void setContainer (Item container) {}

    public Item getContainer() {
        return this.container;
    }	// end public Item getContainer () {}

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }	// end public void setInventory (List<Item> inventory) {}

    public List<Item> getInventory() {
        return this.inventory;
    }	// end public List<Item> getInventory() {}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nContainer Name: ").append(this.getContainer().getName());
        builder.append("\nContainer Description: ").append(this.getContainer().getDescription());
        builder.append("\nInventory Details:\n-----------");

        for (int i = 0; i < this.getInventory().size(); i++) {
            builder.append("\nItem Name: ").append(this.getInventory().get(i).getName());
            builder.append("\nItem Description: ").append(this.getInventory().get(i).getDescription());
            builder.append("\n-----------");
        }	// end for

        return builder.toString();
    }	// end public String toString() {}
}   // end public class InventoryDetails {}
