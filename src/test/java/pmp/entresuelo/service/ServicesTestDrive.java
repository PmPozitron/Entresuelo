package pmp.entresuelo.service;

import java.util.List;
import java.util.Properties;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import pmp.entresuelo.core.InventoryDetails;
import pmp.entresuelo.dao.impl.JdbcCategoryDetailsDao;
import pmp.entresuelo.dao.impl.JdbcInventoryDetailsDao;
import pmp.entresuelo.dao.impl.JdbcItemDao;
import pmp.entresuelo.service.impl.CategoryDetailsManager;
import pmp.entresuelo.service.impl.CategoryDetailsManager;
import pmp.entresuelo.service.impl.InventoryDetailsManager;
import pmp.entresuelo.service.impl.InventoryDetailsManager;

/**
 *
 * @author pozitron for services debug
 */
public class ServicesTestDrive {

    public static void main(String... args) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/entresuelo");
        dataSource.setUsername("entresuelo");
        dataSource.setPassword("07december2014");
        Properties props = new Properties();
        props.put("characterEncoding", "UTF-8");
        dataSource.setConnectionProperties(props);

        JdbcInventoryDetailsDao inventoryDao = new JdbcInventoryDetailsDao();
        inventoryDao.setDataSource(dataSource);

        JdbcItemDao itemDao = new JdbcItemDao();
        itemDao.setDataSource(dataSource);

        JdbcCategoryDetailsDao categoryDetailsDao = new JdbcCategoryDetailsDao();
        categoryDetailsDao.setDataSource(dataSource);

        InventoryDetailsManager inventoryService = new InventoryDetailsManager();
        inventoryService.setInventoryDetailsDao(inventoryDao);
        inventoryService.setItemDao(itemDao);
        
        CategoryDetailsManager categoryDetailsService = new CategoryDetailsManager();
        categoryDetailsService.setCategoryDetailsDao(categoryDetailsDao);
        categoryDetailsService.setItemDao(itemDao);
        
        System.out.println("getAllInventories(): " + inventoryService.getAllInventories());
        System.out.println("getAllCategoryDetails(): " + categoryDetailsService.getAllCategoryDetails());

//        System.out.println(inventory.size());
    }   // end public static void main (String... args) {}

}   // end public class Main {}
