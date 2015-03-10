/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmp.entresuelo.core;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Pozitron
 */
@Deprecated
public class ItemAdder {
    
    private Item item;
    private Item container;
    private List<Category> categories;
    
    private final static Logger logger = Logger.getLogger(ItemAdder.class);
    
    public ItemAdder() {
        
    }
    
    public ItemAdder(Item item, Item container, List<Category> categories) {
        this();
        this.setItem(item);
        this.setContainer(container);
        this.setCategories(categories);
        
        logger.debug(new Date() + " public ItemAdder(Item item, Item container, List<Category> categories) {}");
    }
    
    public Item getItem() {
        return this.item;
    }
    
    public Item getContainer() {
        return this.container;        
    }
    
    public List<Category> getCategories() {
        return this.categories;
    }
    
    public void setItem(Item item) {
        this.item = item;
    }
    
    public void setContainer (Item container) {
        this.container = container;
    }
    
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
}
