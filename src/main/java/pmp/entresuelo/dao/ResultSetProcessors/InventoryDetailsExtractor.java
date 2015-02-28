/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmp.entresuelo.dao.ResultSetProcessors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import pmp.entresuelo.core.InventoryDetails;
import pmp.entresuelo.core.Item;

/**
 *
 * @author pozitron
 */
public class InventoryDetailsExtractor implements ResultSetExtractor {
    
    @Override
    public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<InventoryDetails> inventoryDetails = new ArrayList<InventoryDetails>();
			
	InventoryDetails inventoryDetail = null;
	Item container = null;
	List<Item> inventory = null;
			
	while (rs.next()) {
            if (container != null && container.getId() == rs.getInt("id.item_holder_id")) {
		Item newItem = new Item();
		newItem.setId(rs.getInt("i.id"));
		newItem.setName(rs.getString("i.name"));
                newItem.setDescription(rs.getString("i.description"));
		newItem.setLocationId(container.getLocationId());
		newItem.setLocation(container.getLocation());
					
		List<Item> currentInventory = inventoryDetail.getInventory();					
		currentInventory.add(newItem);
					
            } else {
            
                inventoryDetail = new InventoryDetails();
		container = new Item();
		inventory = new ArrayList<Item>(); 
					
		inventoryDetail.setContainer(container);
		inventoryDetail.setInventory(inventory);
					
		container.setId(rs.getInt("id.item_holder_id"));
		container.setName(rs.getString("ih.name"));
		container.setDescription(rs.getString("ih.description"));
		container.setLocationId(rs.getInt("ih.location_id"));
		container.setLocation(rs.getString("l.name"));
					
		Item newItem = new Item();
		newItem.setId(rs.getInt("i.id"));
		newItem.setName(rs.getString("i.name"));
		newItem.setDescription(rs.getString("i.description"));
		newItem.setLocationId(container.getLocationId());
		newItem.setLocation(container.getLocation());					
					
		List<Item> currentInventory= inventoryDetail.getInventory();					
		currentInventory.add(newItem);	
					
		inventoryDetails.add(inventoryDetail);					
            }	// end if else		
	}	// end while
			
	return inventoryDetails;
    }	// end public Object extractData(ResultSet rs) throws SQLException, DataAccessException {}		
}	// end public class InventoryDetailsExtractor implements ResultSetExtractor {}
