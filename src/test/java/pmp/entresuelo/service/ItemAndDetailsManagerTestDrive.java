/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmp.entresuelo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import pmp.entresuelo.core.Category;
import pmp.entresuelo.core.CategoryDetails;
import pmp.entresuelo.core.InventoryDetails;
import pmp.entresuelo.core.Item;
import pmp.entresuelo.dao.impl.JdbcCategoryDao;
import pmp.entresuelo.dao.impl.JdbcCategoryDetailsDao;
import pmp.entresuelo.dao.impl.JdbcInventoryDetailsDao;
import pmp.entresuelo.dao.impl.JdbcItemDao;
import pmp.entresuelo.service.impl.CategoryDetailsManager;
import pmp.entresuelo.service.impl.CategoryManager;
import pmp.entresuelo.service.impl.InventoryDetailsManager;

/**
 *
 * @author Pozitron
 */
public class ItemAndDetailsManagerTestDrive {

    private DriverManagerDataSource dataSource;

    private JdbcCategoryDao catDao = new JdbcCategoryDao();
    private JdbcItemDao itemDao = new JdbcItemDao();
    private JdbcCategoryDetailsDao catDetsDao = new JdbcCategoryDetailsDao();
    private JdbcInventoryDetailsDao invDetsDao = new JdbcInventoryDetailsDao();

    private AbstractManager catManager = new CategoryManager();
    private InventoryDetailsManager invDetsManager = new InventoryDetailsManager();
    private CategoryDetailsManager catDetsManager = new CategoryDetailsManager();
    private ItemAndDetailsManager itemManager;
    
    private DataSourceTransactionManager txManager;

    public static void main(String[] args) {
        ItemAndDetailsManagerTestDrive testDrive = new ItemAndDetailsManagerTestDrive();
//        testDrive.setUp();
        testDrive.setUpFromXml();
        testDrive.startTest();

    }

    private void startTest() {
        Item newItem = new Item(999, "testNewManager", "we are testing new manager", 1, "лоджия");
        List<Category> categories = new ArrayList<Category>();
        Category catOne = catManager.getEntityById(1);
        Category catTwo = catManager.getEntityById(2);
        Category catThree = new Category(-1, "fake", "counterfeit");    // with this string transaction should not be commited
        categories.add(catOne);
        categories.add(catTwo);
        categories.add(catThree);

        CategoryDetails catDets = new CategoryDetails(newItem, categories);

        Item container = itemManager.getItemById(1);
        List<Item> inventory = new ArrayList<Item>();
        inventory.add(newItem);
        InventoryDetails invDets = new InventoryDetails(container, inventory);

        int itemsCountBeforeTx = itemManager.getAllItems().size();
        int catDetsCountBeforeTx = itemManager.getAllCategoryDetails().size();
        int invDetsBeroreTx = itemManager.getAllInventories().size();
        int itemsInContainerBeforeTx = itemManager.getInventoryDetailsByItemId(container.getId()).getInventory().size();

        System.out.println("itemsCountBeforeTx " + itemsCountBeforeTx);
        System.out.println("catDetsCountBeforeTx " + catDetsCountBeforeTx);
        System.out.println("invDetsBeroreTx " + invDetsBeroreTx);
        System.out.println("itemsInContainerBeforeTx " + itemsInContainerBeforeTx);

        itemManager.addItemWithDetails(newItem, catDets, invDets);

        int itemsCountAfterTx = itemManager.getAllItems().size();
        int catDetsCountAfterTx = itemManager.getAllCategoryDetails().size();
        int invDetsAfterTx = itemManager.getAllInventories().size();
        int itemsInContainerAfterTx = itemManager.getInventoryDetailsByItemId(container.getId()).getInventory().size();

        System.out.println("itemsCountAfterTx " + itemsCountAfterTx);
        System.out.println("catDetsCountAfterTx " + catDetsCountAfterTx);
        System.out.println("invDetsAfterTx " + invDetsAfterTx);
        System.out.println("itemsInContainerAfterTx " + itemsInContainerAfterTx);
    }

    private void setDataSource() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/entresuelo");
        dataSource.setUsername("entresuelo");
        dataSource.setPassword("07december2014");
        Properties props = new Properties();
        props.put("characterEncoding", "UTF-8");
        dataSource.setConnectionProperties(props);
    }

    private void setUpDaos() {
        catDao.setDataSource(dataSource);
        itemDao.setDataSource(dataSource);
        catDetsDao.setDataSource(dataSource);
        invDetsDao.setDataSource(dataSource);
    }

    private void setUpManagers() {
        ((CategoryManager)catManager).setCategoryDao(catDao);
        invDetsManager.setItemDao(itemDao);
        invDetsManager.setInventoryDetailsDao(invDetsDao);
        catDetsManager.setItemDao(itemDao);
        catDetsManager.setCategoryDao(catDao);
        catDetsManager.setCategoryDetailsDao(catDetsDao);
        itemManager.setCategoryDao(catDao);
        itemManager.setItemDao(itemDao);
        itemManager.setCategoryDetailsDao(catDetsDao);
        itemManager.setInventoryDetailsDao(invDetsDao);
    }

    private void setUpFromXml() {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("new-item-manager.xml");
        AbstractManager cdm = ctx.getBean("categoryDetailsManager", AbstractManager.class);        
        AbstractManager cm = ctx.getBean("categoryManager", AbstractManager.class);
        
        itemManager = ctx.getBean("itemManager", ItemAndDetailsManager.class);  
        catManager = ctx.getBean("categoryManager", AbstractManager.class);       
    }

    private void setUp() {
        setDataSource();
        setUpDaos();
        setUpManagers();
    }
}

class ItemAndDetailsManagerImpl extends ItemAndDetailsManager {

}
