/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmp.entresuelo.dao.ResultSetProcessors;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import pmp.entresuelo.core.Item;

/**
 *
 * @author pozitron
 */
public class ItemMapper implements ParameterizedRowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item (rs.getInt("i.id"), rs.getString("i.name"), rs.getString("i.description"), 
                rs.getInt("l.id"),rs.getString("l.name"));	
			
	return item;
    }   // end public Object mapRow(ResultSet rs, int rowNum) throws SQLException {}		
}   // end public class ItemMapper {}
