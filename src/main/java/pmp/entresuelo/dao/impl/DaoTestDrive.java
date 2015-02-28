package pmp.entresuelo.dao.impl;

import java.util.List;
import java.util.Properties;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import pmp.entresuelo.core.InventoryDetails;

/**
 *
 * @author pozitron для проверки реализации некоторых дао и мапперов
 */
public class DaoTestDrive {

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

        System.out.println("itemDao " + itemDao.getEntityByName("молоток").get(0).getDescription());

        System.out.println("inventoryDao " + inventoryDao.getEntityByName("стеллаж").size());
        System.out.println("inventoryDao " + inventoryDao.getInventoryByContainerName("стеллаж").size());

        System.out.println("categoryDao " + categoryDetailsDao.getEntityByName("скотч").size());
        System.out.println("categoryDao " + categoryDetailsDao.getCategoriesByItemName("скотч").size());
        System.out.println();

//        System.out.println(inventory.size());
    }   // end public static void main (String... args) {}

}   // end public class Main {}
