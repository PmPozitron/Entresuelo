/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmp.entresuelo.dao.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import pmp.entresuelo.core.Item;
import pmp.entresuelo.dao.AbstractDao;
import pmp.entresuelo.dao.ResultSetProcessors.ItemMapper;

/**
 *
 * @author Pozitron
 */
public class NPJdbcItemDao extends NamedParameterJdbcTemplate implements AbstractDao {

    public NPJdbcItemDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public <T> List<T> getAllEntities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> List<T> getEntityByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item getEntityById(int id) {
        List<Item> items = super.query(AbstractDao.SELECT_FROM_ITEMS + " WHERE i.id = :id", new MapSqlParameterSource("id", id), new ItemMapper());
        return items.get(0);
    }

    @Override
    public <T> int addNewEntity(T newEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> void updateEntity(int idToUpdate, T updatedEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> int updateEntity(T updatedEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteEntity(int idToDelete) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
