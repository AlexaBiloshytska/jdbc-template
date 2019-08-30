import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import entity.Products;
import mapper.ProductMapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

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
        List<Products> queryList = namedParameterJDBCTemplate.query(query, mapper, "Huawei P20 Lite","Mobile");

        Assert.assertEquals( "Huawei P20 Lite", queryList.get(0).getName());
    }

    @Test
    public void updateWithVarargsTest(){
        String updateQuery = "update products where name =? and category =?";
        int update = namedParameterJDBCTemplate.update(updateQuery, mapper);
    }
}