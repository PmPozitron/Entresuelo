/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmp.entresuelo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pmp.entresuelo.core.Category;
import pmp.entresuelo.core.CategoryDetails;
import pmp.entresuelo.core.InventoryDetails;
import pmp.entresuelo.core.Item;
import pmp.entresuelo.core.SimpleItemAdder;
import pmp.entresuelo.dao.AbstractDao;
import pmp.entresuelo.dao.impl.JdbcCategoryDetailsDao;
import pmp.entresuelo.dao.impl.JdbcInventoryDetailsDao;

/**
 *
 * @author Pozitron
 */
@Transactional
public abstract class ItemAndDetailsManager {

    private static final Logger logger = Logger.getLogger(ItemAndDetailsManager.class);

    @Autowired
    AbstractDao itemDao;

    @Autowired
    AbstractDao categoryDao;

    @Autowired
    AbstractDao categoryDetailsDao;

    @Autowired
    AbstractDao inventoryDetailsDao;

    public void setItemDao(AbstractDao itemDao) {
        this.itemDao = itemDao;
    }

    public void setCategoryDetailsDao(AbstractDao categoryDetailsDao) {
        this.categoryDetailsDao = categoryDetailsDao;
    }

    public void setInventoryDetailsDao(AbstractDao inventoryDetailsDao) {
        this.inventoryDetailsDao = inventoryDetailsDao;
    }

    public void setCategoryDao(AbstractDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Item> getAllItems() {
        return itemDao.getAllEntities();
    }

    public List<CategoryDetails> getAllCategoryDetails() {
        List<CategoryDetails> categoryDetails = new ArrayList<CategoryDetails>();
        List<Item> items = this.itemDao.getAllEntities();

        for (Item item : items) {
            CategoryDetails categoryDetail = ((JdbcCategoryDetailsDao) categoryDetailsDao).getCategoryDetailsByItem(item);
            categoryDetails.add(categoryDetail);
        }

        return categoryDetails;
    }

    public List<InventoryDetails> getAllInventories() {
        List<Item> allItems = this.itemDao.getAllEntities();
        List<InventoryDetails> allInventoryDetails = new ArrayList<InventoryDetails>();

        for (Item item : allItems) {
            InventoryDetails inventoryDetails = ((JdbcInventoryDetailsDao) inventoryDetailsDao).getInventoryDetailsByContainer(item);

            if (inventoryDetails.getInventory().isEmpty()) {
                continue;
            }

            allInventoryDetails.add(inventoryDetails);
        }

        return allInventoryDetails;
    }

    public List<Item> getAllContainers() {
        return ((JdbcInventoryDetailsDao) inventoryDetailsDao).getAllContainers();
    }

    public Item getItemById(int id) {
        if (id < 1) {
            throw new IllegalArgumentException(new Date() + " IllegalArgumentException in getItemById()");
        }

        return itemDao.getEntityById(id);
    }

    public CategoryDetails getCategoryDetailsByItemId(int id) {
        if (id < 1) {
            throw new IllegalArgumentException(new Date() + " IllegalArgumentException in getItemById()");
        }

        return categoryDetailsDao.getEntityById(id);
    }

    public InventoryDetails getInventoryDetailsByItemId(int id) {
        if (id < 1) {
            throw new IllegalArgumentException(new Date() + " IllegalArgumentException in getItemById()");
        }

//        return inventoryDetailsDao.getEntityById(id);
        Item container = itemDao.getEntityById(id);
        return ((JdbcInventoryDetailsDao) inventoryDetailsDao).getInventoryDetailsByContainer(container);
    }

    public CategoryDetails getNewCategoryDetailsByAdder(SimpleItemAdder itemAdder) {

        CategoryDetails cd = new CategoryDetails();
        cd.setItem(itemAdder.getItem());
        List<Category> categories = new ArrayList<Category>();
        cd.setCategories(categories);

        if (itemAdder.getCategories() != null) {
            for (int id : itemAdder.getCategories()) {
                cd.getCategories().add((Category) this.categoryDao.getEntityById(id));
            }
        }

        return cd;
    }

    public InventoryDetails getNewInventoryDetailsByAdder(SimpleItemAdder itemAdder) {

        InventoryDetails id = new InventoryDetails();
//        id.setContainer((Item)this.itemDao.getEntityById(itemAdder.getContainerId()));
        id.setContainer((itemAdder.getContainerId() == -1 || itemAdder.getContainerId() == 0)
                ? null : ((Item) this.itemDao.getEntityById(itemAdder.getContainerId()))
        );

        List<Item> inventory = new ArrayList<Item>();
        id.setInventory(inventory);
        id.getInventory().add(itemAdder.getItem());

        return id;
    }

    public Item getContainerByItemId(int id) {
        if (id < 1) {
            throw new IllegalArgumentException(new Date() + " IllegalArgumentException in getItemById()");
        }

        Item item = itemDao.getEntityById(id);

        return ((JdbcInventoryDetailsDao) this.inventoryDetailsDao).getContainerByItem(item);
    }

    public int addItem(Item newItem) {
        if (newItem == null) {
            return 0;
        }

        return itemDao.addNewEntity(newItem);
    }

    public int addCategoryDetails(CategoryDetails newCategoryDetails) {
        if (newCategoryDetails == null || newCategoryDetails.getCategories().isEmpty()) {
            return 0;
        }

        return categoryDetailsDao.addNewEntity(newCategoryDetails);
    }

    public int addInventoryDetails(InventoryDetails newInventoryDetails) {
        if (newInventoryDetails == null || newInventoryDetails.getInventory().isEmpty()) {
            return 0;
        }

        return inventoryDetailsDao.addNewEntity(newInventoryDetails);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int addItemWithDetails(Item newItem, CategoryDetails newCategoryDetails, InventoryDetails newInventoryDetails) {
        int newItemId = addItem(newItem);
        newCategoryDetails.getItem().setId(newItemId);
        newInventoryDetails.getInventory().get(0).setId(newItemId);
        addCategoryDetails(newCategoryDetails);
        addInventoryDetails(newInventoryDetails);

        return newItemId;
    }

    public int updateItem(Item updatedItem) {
        if (updatedItem == null) {
            return 0;
        }

        return itemDao.updateEntity(updatedItem);
    }

    public int updateCategoryDetails(CategoryDetails updatedCategoryDetails) {
        if (updatedCategoryDetails == null || updatedCategoryDetails.getCategories().isEmpty()) {
            return 0;
        }

        return categoryDetailsDao.updateEntity(updatedCategoryDetails);
    }

    public int updateInventoryDetails(InventoryDetails updatedDetails) {
        if (updatedDetails == null) {
            throw new IllegalArgumentException(new Date() + " IllegalArgumentException in updateInventoryDetails()");
        }
//      there should be only one item in inventory in context calling this method.
//      there's only possibility to move one particular item from one container to another via editItemDetails functionality.  
        Item oldContainer = getContainerByItemId(updatedDetails.getInventory().get(0).getId());
        int oldContainerId;
        if (oldContainer == null) {
            oldContainerId = -1;
        
        } else {
            oldContainerId = getContainerByItemId(updatedDetails.getInventory().get(0).getId()).getId();
        }
        int newContainerId = -1;

        if (updatedDetails.getContainer() == null || updatedDetails.getContainer().getId() == 0 || updatedDetails.getContainer().getId() == -1) {
            // we do nothing here cause there's special check on the dao layer for newContainerId
        } else {
            newContainerId = updatedDetails.getContainer().getId();
        }

        return updateContainerForItem(oldContainerId, newContainerId, updatedDetails.getInventory().get(0).getId());
    }

    public int updateContainerForItem(int oldContainerId, int newContainerId, int itemId) {
        return ((JdbcInventoryDetailsDao) inventoryDetailsDao).updateContainerForItem(oldContainerId, newContainerId, itemId);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int updateItemWithDetails(Item updatedItem, CategoryDetails updatedCatDets, InventoryDetails updatedInvDets) {
        return updateItem(updatedItem) + updateCategoryDetails(updatedCatDets) + updateInventoryDetails(updatedInvDets);
    }

    public int deleteItemById(int id) {
        if (id < 1) {
            return 0;   // TODO: maybe IllegalArgumentException is more apppropriate here ?
        }
        return itemDao.deleteEntity(id);
    }

    public int deleteCategoryDetailsByItemId(int id) {
        if (id < 1) {
            return 0;   // TODO: maybe IllegalArgumentException is more apppropriate here ?
        }

        return categoryDetailsDao.deleteEntity(id);
    }
//  this should remove item being deleted from it's container

    public int deleteInventoryDetailsByItemId(int itemId) {
        if (itemId < 1) {
            return 0;   // TODO: maybe IllegalArgumentException is more apppropriate here ?
        }

        int containerId = getContainerByItemId(itemId).getId();
        return ((JdbcInventoryDetailsDao) inventoryDetailsDao).deleteInventoryDetail(containerId, itemId);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int deleteItemWithDetails(int itemId) {
        return deleteCategoryDetailsByItemId(itemId) + deleteInventoryDetailsByItemId(itemId) + deleteItemById(itemId);
    }

}
