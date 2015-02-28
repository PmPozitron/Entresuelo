/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmp.entresuelo.dao.ResultSetProcessors;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import pmp.entresuelo.core.Location;

/**
 *
 * @author pozitron
 */
public class LocationMapper implements ParameterizedRowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Location location = new Location (rs.getInt("l.id"), rs.getString("l.name"), rs.getString("l.description"));			
       return location;
    }	// end public Object mapRow(ResultSet rs, int rowNum) throws SQLException {}		
}   // end private static class LocationMapper {}