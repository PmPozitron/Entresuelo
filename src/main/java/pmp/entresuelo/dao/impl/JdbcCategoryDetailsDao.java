package pmp.entresuelo.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.SimpleLayout;

import pmp.entresuelo.dao.AbstractDao;
import pmp.entresuelo.core.Category;
import pmp.entresuelo.core.CategoryDetails;
import pmp.entresuelo.core.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
//import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import pmp.entresuelo.dao.ResultSetProcessors.CategoryDetailsExtractor;
import pmp.entresuelo.dao.ResultSetProcessors.CategoryMapper;
import pmp.entresuelo.dao.ResultSetProcessors.ItemMapper;

//@Component("categoryDetailsDao")
public class JdbcCategoryDetailsDao extends JdbcTemplate implements AbstractDao {
    private static final Logger logger = Logger.getLogger(JdbcCategoryDetailsDao.class);
    private static final ConsoleAppender consoleLog = new ConsoleAppender (new SimpleLayout(), ConsoleAppender.SYSTEM_OUT);
	
    private static void initLogger () {
        JdbcCategoryDetailsDao.logger.addAppender(JdbcCategoryDetailsDao.consoleLog);
	JdbcCategoryDetailsDao.logger.setLevel(Level.ALL);
		
	JdbcCategoryDetailsDao.logger.debug(new Date() + " private static void initLogger () {}");
    }	// end private static void initLogger () {}
	
    static {
	JdbcCategoryDetailsDao.initLogger();
    }	// end static
	
    private static final String SELECT_CATEGORIES = 
              "SELECT * "
            + "FROM entresuelo.item AS i "
            + "INNER JOIN entresuelo.categories_details AS cd "
            + "ON cd.item_id = i.id "
            + "INNER JOIN entresuelo.category AS c "
            + "ON cd.category_id = c.id "
            + "INNER JOIN entresuelo.location as l "
            + "ON i.location_id = l.id ";
    
//    @Autowired
//    DataSource dataSource;
        
    public JdbcCategoryDetailsDao () {
        super();
	JdbcCategoryDetailsDao.logger.debug(new Date() + " public JdbcCategoryDetailsDao () {}");		
    }	// end public JdbcCategoryDetailsDao () {}

//  С‚Р°Рє РєР°Рє CategoryDetails - СЌС‚Рѕ С…Р°СЂР°РєС‚РµСЂРёСЃС‚РёРєР° Item, С‚Рѕ РґР°РЅРЅС‹Р№ РјРµС‚РѕРґ С‚СЂРµР±СѓРµС‚ РЅР° РІС…РѕРґ РёРјСЏ Item, РґР»СЏ РєРѕС‚РѕСЂРѕРіРѕ РЅСѓР¶РЅРѕ РЅР°Р№С‚Рё РґР°РЅРЅС‹Рµ
//  07/12/14: РјРµС‚РѕРґ РѕСЃС‚Р°РІР»РµРЅ РІ РєРѕРґРµ, РѕРґРЅР°РєРѕ РїСЂРµРґРїРѕС‡С‚РµРЅРёРµ РІ РёСЃРї-РёРё Р±СѓРґРµС‚ Сѓ getCategoriesByItemName()
    @Override
    public List <CategoryDetails> getEntityByName(String itemName) {
	JdbcCategoryDetailsDao.logger.debug(new Date() + " public List <CategoryDetails> getEntityByName(String itemName) {}");
		
	List<CategoryDetails> categoryDetails = (List<CategoryDetails>) super.query(JdbcCategoryDetailsDao.SELECT_CATEGORIES
				  + " WHERE i.name = '" + itemName + "';", new CategoryDetailsExtractor());  

	return categoryDetails;
    }	// end public List <Category> getEntityByName(String name) {}
	
    @Override
    public List<CategoryDetails> getAllEntities() {
	JdbcCategoryDetailsDao.logger.debug(new Date() + " public List<CategoryDetails> getAllEntities() {}");
		
	List<CategoryDetails> categoryDetails = (List<CategoryDetails>) super.query(JdbcCategoryDetailsDao.SELECT_CATEGORIES, 
                new CategoryDetailsExtractor());	
        
        return categoryDetails;
    }	// end public List<?> getAllEntities() {}

    @Override
    public <T> int addNewEntity(T newEntity) {
        JdbcCategoryDetailsDao.logger.debug(new Date() + " public <T> int addNewEntity(T newEntity) {}");
		
        int count = 0;
        for (Category category : ((CategoryDetails)newEntity).getCategories()) {
            super.update(AbstractDao.INSERT_INTO_CATEGORY_DETAILS + "(" + ((CategoryDetails)newEntity).getItem().getId() + "," + category.getId() + ")");
            count ++;
        }
        return count;        
    }	// end public <T> int addNewEntity(T newEntity) {} 

    @Override
    public <T> void updateEntity(int idToUpdate, T updatedEntity) {
        JdbcCategoryDetailsDao.logger.debug(new Date() + " public <T> void updateEntity(int idToUpdate, T updatedEntity) {}");
		
        throw new UnsupportedOperationException();			
    }	// end public <T> void updateEntity(int idToUpdate, T updatedEntity) {}

    @Override
    public int deleteEntity(int idToDelete) {
        JdbcCategoryDetailsDao.logger.debug(new Date() + " public void deleteEntity(int idToDelete) {}");
		
	throw new UnsupportedOperationException();
    }	// end public void deleteEntity(int idToDelete) {}
    
    public List <Category> getCategoriesByItemName (String itemName) {
        JdbcCategoryDetailsDao.logger.debug(new Date() + " public List <Category> getCategoriesByItemName (String itemName) {}");
        
        List <Category> categories = super.query(AbstractDao.SELECT_FROM_CATEGORY_DETAILS + " WHERE i.name = '" + itemName + "'",
                new CategoryMapper());
        
        return categories;
    }   // end public List <Category> getCategoriesByItemName (String itemName) {}
    
    public CategoryDetails getCategoryDetailsByItem(Item item) {
        JdbcCategoryDetailsDao.logger.debug(new Date() + " public CategoryDetails getCategoriesByItem(Item item) {}");
        
        List<Category> categories = this.getCategoriesByItemName(item.getName());
        CategoryDetails categoryDetails = new CategoryDetails(item, categories);
        
        return categoryDetails;
    }   // end public CategoryDetails getCategoriesByItem(Item item) {}

    @Override
    public CategoryDetails getEntityById(int id) {
        JdbcCategoryDetailsDao.logger.debug(new Date() + " public Item getEntityById(int id) {}");
        List<CategoryDetails> categoryDetails = (List<CategoryDetails>)super.query(AbstractDao.SELECT_FROM_CATEGORY_DETAILS + " WHERE cd.item_id = " + id + ";", 
                new CategoryDetailsExtractor());

        if (categoryDetails.size() == 0) {
            return new CategoryDetails();
        }
        return categoryDetails.get(0);
        
//        throw  new UnsupportedOperationException("JdbcCategoryDetailsDao.getEntityById() - don't see any sense in this method here, 28/02/2015");
    }   // end public Item getEntityById(int id) {}

    @Override
    public <T> int updateEntity(T updatedEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int deleteCategoriesForItem(Item item) {
        return super.update(AbstractDao.DELETE_FROM_CATEGORY_DETAILS + " WHERE item_id = " + item.getId());
    }
    
    public int insertCategoryDetailsForItem(List<Category> categories, Item item) {
        int count = 0;
        for (Category cat : categories) {
            count += super.update(AbstractDao.INSERT_INTO_CATEGORY_DETAILS + "(" + item.getId() + ", " + cat.getId() + ")");
        }
        return count;
    }

    public int insertCategoryDetailsForItem(int[] categoryIds, Item item) {
        int count = 0;
        for (int cat : categoryIds) {
            count += super.update(AbstractDao.INSERT_INTO_CATEGORY_DETAILS + "(" + item.getId() + ", " + cat + ")");
        }
        return count;
    }
}   // end public class CategoryDao extends SimpleJdbcDaoSupport implements AbstractDao {}
