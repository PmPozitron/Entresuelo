/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmp.entresuelo.dao.ResultSetProcessors;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import pmp.entresuelo.core.Category;

/**
 *
 * @author pozitron
 */

public class CategoryMapper implements ParameterizedRowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category (rs.getInt("c.id"), rs.getString("c.name"), rs.getString("c.description"));	
			
        return category;
    }	// end public Object mapRow(ResultSet rs, int rowNum) throws SQLException {}		
}   // end private static class CategoryMapper {}