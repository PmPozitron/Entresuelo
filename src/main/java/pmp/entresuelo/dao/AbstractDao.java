package pmp.entresuelo.dao;

import java.util.List;

public interface AbstractDao {
    String SELECT_FROM_ITEMS =
              "SELECT i.id, i.name, i.description, l.id, l.name "
            + "FROM entresuelo.item AS i "
            + "INNER JOIN entresuelo.location AS l "
            + "ON i.location_id = l.id ";
    
    String SELECT_FROM_LOCATIONS =
              "SELECT l.id, l.name, l.description "
            + "FROM entresuelo.location AS l ";
	
    String SELECT_FROM_CATEGORIES = 
              "SELECT id, name, description "
            + "FROM entresuelo.category AS c ";
    
//  alias 'i' should be granted to second 'item' table being joined (containing inventory of a holder);
//  it's needed for usage of ItemMapper in JbdcInventoryDetailsDao    
    String SELECT_FROM_INVENTORY_DETAILS =
              "SELECT i.*, l.id, l.name, id.item_holder_id, ih.name, ih.description, ih.location_id "
            + "FROM entresuelo.inventory_details AS id "
            + "INNER JOIN entresuelo.item AS ih "
            + "ON id.item_holder_id = ih.id "
            + "INNER JOIN entresuelo.location AS l "
            + "ON ih.location_id = l.id "
            + "INNER JOIN entresuelo.item AS i "
            + "ON i.id = id.stored_item_id ";
    
    String SELECT_FROM_CATEGORY_DETAILS =
              "SELECT * "
            + "FROM entresuelo.item AS i "
            + "INNER JOIN entresuelo.categories_details AS cd "
            + "ON cd.item_id = i.id "
            + "INNER JOIN entresuelo.category AS c "
            + "ON cd.category_id = c.id "
            + "INNER JOIN entresuelo.location as l "
            + "ON i.location_id = l.id ";  
    
    String INSERT_INTO_ITEMS = 
            "INSERT INTO entresuelo.item (name, description, location_id) "
            + "VALUES ";
    
    String INSERT_INTO_LOCATIONS = 
            "INSERT INTO entresuelo.location (name, description) "
            + "VALUES ";
    
    String INSERT_INTO_CATEGORIES = 
            "INSERT INTO entresuelo.category (name, description) "
            + "VALUES ";

    String INSERT_INTO_CATEGORY_DETAILS = 
            "INSERT INTO entresuelo.categories_details (item_id, category_id) "
            + "VALUES ";

    String INSERT_INTO_INVENTORY_DETAILS = 
            "INSERT INTO entresuelo.inventory_details (item_holder_id, stored_item_id) "
            + "VALUES ";
    
    String UPDATE_ITEM = 
            "UPDATE entresuelo.item SET ";
    
    String DELETE_FROM_INVENTORY_DETAILS = 
            "DELETE FROM entresuelo.inventory_details ";       

    String DELETE_FROM_CATEGORY_DETAILS = 
            "DELETE FROM entresuelo.categories_details ";       
    
    <T> List <T> getAllEntities();
    <T> List<T> getEntityByName(String name);
    <T> T getEntityById(int id);
    <T> int addNewEntity(T newEntity);
    <T> void updateEntity(int idToUpdate, T updatedEntity);
    <T> int updateEntity(T updatedEntity);
    int deleteEntity(int idToDelete);    

}   // end public interface AbstractDao {}
