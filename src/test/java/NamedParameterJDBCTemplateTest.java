import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import entity.Product;
import mapper.ProductMapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamedParameterJDBCTemplateTest {

    private ProductMapper mapper = new ProductMapper();
    private static NamedParameterJDBCTemplate namedParameterJDBCTemplate;

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    @BeforeClass
    public static void setup() {
        config.setUsername("sa");
        config.setPassword("");
        config.setJdbcUrl("jdbc:h2:mem:test;INIT=runscript from 'classpath:init.sql'");
        config.setDriverClassName("org.h2.Driver");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);

        namedParameterJDBCTemplate = new NamedParameterJDBCTemplate(dataSource);
    }

    @Test
    public void queryWithVarargsTest() {
        String query = "select * from products where name = ? and category = ?";
        List<Product> queryList = namedParameterJDBCTemplate.query(query, mapper, "Huawei P20 Lite", "Mobile");

        Assert.assertEquals("Huawei P20 Lite", queryList.get(0).getName());
    }

    @Test
    public void updateWithVarargsTest() {
        String updateQuery = "update products set name=?, category=? where category=?";
        int update = namedParameterJDBCTemplate.update(updateQuery, "samsung", "Flagman", "Mobile");
        Assert.assertEquals(2, update);

        String selectQuery = "select * from products where category =?";
        List<Product> products = namedParameterJDBCTemplate.query(selectQuery, mapper, "Flagman");
        Assert.assertEquals("samsung",products.get(0).getName());
        Assert.assertEquals("samsung",products.get(1).getName());
        Assert.assertEquals("Flagman", products.get(0).getCategory());
        Assert.assertEquals("Flagman", products.get(1).getCategory());
    }

    @Test
    public void queryForObjectVarargs() {
        String query = "select * from products where category =?";
        Product product = namedParameterJDBCTemplate.queryForObject(query, mapper, "Computers");
        Assert.assertEquals("Computers", product.getCategory());

        Product products = namedParameterJDBCTemplate.queryForObject(query, mapper, "Mobile");
        Assert.assertEquals("Mobile", products.getCategory());
    }

    @Test
    public void query(){
        Map<String, Object> map = new HashMap<>();
        map.put("category","Mobile");
        map.put("name123dfgdfgdf","Samsung S 10");//note this will go first due to the key hashcode

        String query = "select * from products where name=:name123dfgdfgdf and category=:category";

        List<Product> products = namedParameterJDBCTemplate.query(query, mapper, map);
        Assert.assertEquals(1, products.size());

    }
}