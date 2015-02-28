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
import pmp.entresuelo.core.Category;
import pmp.entresuelo.core.CategoryDetails;
import pmp.entresuelo.core.Item;

/**
 *
 * @author pozitron
 */

public class CategoryDetailsExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
		
        List<CategoryDetails> categoryDetails = new ArrayList<CategoryDetails>();
		
        CategoryDetails categoryDetail = null;
        Item item = null;
        List<Category> categories = null;
			
        while (rs.next()) {
            if (item != null && item.getId() == rs.getInt("i.id")) {
                Category newCategory = new Category();
                newCategory.setId(rs.getInt("c.id"));
                newCategory.setName(rs.getString("c.name"));
                newCategory.setDescription(rs.getString("c.description"));
                List<Category> currentCategories = categoryDetail.getCategories();					
                currentCategories.add(newCategory);
					
            } else {
		
                categoryDetail = new CategoryDetails();
                item = new Item();
                categories = new ArrayList<Category>(); 
					
                categoryDetail.setItem(item);
                categoryDetail.setCategories(categories);
					
                item.setId(rs.getInt("i.id"));
                item.setName(rs.getString("i.name"));
                item.setDescription(rs.getString("i.description"));
                item.setLocationId(rs.getInt("i.location_id"));
                item.setLocation(rs.getString("l.name"));
					
                Category newCategory = new Category();
                newCategory.setId(rs.getInt("c.id"));
                newCategory.setName(rs.getString("c.name"));
                newCategory.setDescription(rs.getString("c.description"));
			
                List<Category> currentCategories = categoryDetail.getCategories();					
                currentCategories.add(newCategory);	
					
                categoryDetails.add(categoryDetail);
					
                System.out.println("categoryDetails.size " + categoryDetails.size());
					
            }   // end if else		
        }   // end while
			
        return categoryDetails;
    }   // end public Object extractData(ResultSet rs) throws SQLException, DataAccessException {}		
}   // end public class CategoryDetailsExtractor implements ResultSetExtractor {}