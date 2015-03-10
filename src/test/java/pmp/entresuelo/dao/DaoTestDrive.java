package pmp.entresuelo.dao;

import java.util.List;
import java.util.Properties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import pmp.entresuelo.core.InventoryDetails;
import pmp.entresuelo.dao.ResultSetProcessors.ItemMapper;
import pmp.entresuelo.dao.impl.JdbcCategoryDetailsDao;
import pmp.entresuelo.dao.impl.JdbcInventoryDetailsDao;
import pmp.entresuelo.dao.impl.JdbcItemDao;
import pmp.entresuelo.dao.impl.NPJdbcItemDao;

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

//      TODO NamedParameterJdbcTemplate should be used in AbstractDao implementation instead JdbcTemplate - named params are cool !!!
        NamedParameterJdbcTemplate npJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        String sqlQuery = AbstractDao.SELECT_FROM_ITEMS + " WHERE i.id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", 1);
        System.out.println(npJdbcTemplate.query(sqlQuery, params, new ItemMapper()));

        System.out.println(dataSource.hashCode());

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-database.xml");
        dataSource = ctx.getBean("dataSource", org.springframework.jdbc.datasource.DriverManagerDataSource.class);

        System.out.println(dataSource.hashCode());

        NPJdbcItemDao npItemDao = new NPJdbcItemDao(dataSource);
        System.out.println(npItemDao.getEntityById(1).getName());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        System.out.println(npJdbcTemplate.query(sqlQuery, params, new ItemMapper()));     

//        System.out.println(inventory.size());
    }   // end public static void main (String... args) {}

}   // end public class Main {}
