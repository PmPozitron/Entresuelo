/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmp.entresuelo.core;

import java.util.List;

/**
 *
 * @author Pozitron
 */
//Primitives are used for referencing to container and categories
//TODO: think of it = it seems that using ItemAdderValidator (with 'pmp.entresuelo.core' entities) needs custom converter
public class SimpleItemAdder {
    
    private Item item;
    private int containerId;
    private List<Integer> categories;
    
    public SimpleItemAdder() {
        
    }
    
    public SimpleItemAdder(Item item, int containerId, List<Integer> categoryIds) {
        this();
        this.setItem(item);
        this.setContainerId(containerId);
        this.setCategories(categories);
    }
    
    public Item getItem() {
        return this.item;
    }
    
    public int getContainerId() {
        return this.containerId;        
    }
    
    public List<Integer> getCategories() {
        return this.categories;
    }
    
    public void setItem(Item item) {
        this.item = item;
    }
    
    public void setContainerId (int containerId) {
        this.containerId = containerId;
    }
    
    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }
    
}
