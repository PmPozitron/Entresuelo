package pmp.entresuelo.core;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.SimpleLayout;

public class CategoryDetails implements Serializable {

    private static final Logger logger = Logger.getLogger(CategoryDetails.class);
    private static final ConsoleAppender consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_OUT);

    private static void initLogger() {
        CategoryDetails.logger.addAppender(consoleLog);
        CategoryDetails.logger.setLevel(Level.ALL);

        CategoryDetails.logger.debug(new Date() + " private static void initLogger() {}");
    }	// end private static void initLogger() {}

    static {
        CategoryDetails.initLogger();
    }	// end static

//	private int itemId;
//	private int [] cateroryIds;
    private Item item;
    private List<Category> categories;

    public CategoryDetails() {
        super();
//	CategoryDetails.initLogger();
        CategoryDetails.logger.debug(new Date() + " public CategoryDetails() {}");
    }	// end public CategoryDetails() {}

    public CategoryDetails(Item item, List<Category> categories) {
        super();
//	CategoryDetails.initLogger();
        CategoryDetails.logger.debug(new Date() + " public CategoryDetails(int itemId, int[]categoryIds) {}");

        this.setItem(item);
        this.setCategories(categories);
    }	// end public CategoryDetails(int itemId, int[]categoryIds) {}

    public void setItem(Item item) {
        this.item = item;
    }	// end public void setItemId(int itemId) {}

    public Item getItem() {
        return this.item;
    }	// end public int getItemId() {}

//	public void setCategoryIds(int [] categoryIds) {
//	this.cateroryIds = categoryIds;
//	}	// end public void setCategoryIds(int [] categoryIds) {}
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }	// end public void setCategories(Map<Integer, String> categories) {}

    public List<Category> getCategories() {
        return this.categories;
    }	// end public void getCategories() {}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nItem Name: ").append(this.getItem().getName());
        builder.append("\nItem Description: ").append(this.getItem().getDescription());
        builder.append("\nCategories Details:\n-----------");

        for (int i = 0; i < this.getCategories().size(); i++) {

            builder.append("\nCategory Name: ").append(this.getCategories().get(i).getName()).append(";");
            builder.append("\nCategory Description: ").append(this.getCategories().get(i).getDescription()).append(";");
            builder.append("\n-----------");
        }	// end for		

        return builder.toString();
    }	// end public String toString() {}

    @Override
    public boolean equals(Object o) {
        if (this == null && o == null) {
            return true;
        }

        if ((this == null && o != null)
                || (this != null && o == null)) {
            return false;
        }

        CategoryDetails cd = (CategoryDetails) o;
        
        if (!this.getItem().equals(cd.getItem())) {
            return false;
        }
        
        for (Category thisCategory : this.getCategories()) {
            for (Category cdCategory : cd.getCategories()) {
                if (!thisCategory.equals(cdCategory)) {
                    return false;
                }
            }
        }
        
        return true;
    }

    @Override
    public int hashCode() {        
        return this.item.hashCode() + this.categories.get(0).hashCode();
    }
}	// end public class CategoryDetails {}
