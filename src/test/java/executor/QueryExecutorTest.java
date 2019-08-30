package executor;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class QueryExecutorTest {
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
    }

    @Test
    public void executeQueryTest() {
        List<String> list = new ArrayList<>();
        list.add("Huawei");
        list.add("Mobile");
        String sqlQuery = "select * from products where name = ? and category=?";
        //ResultSet resultSet = queryExecutor.executeQuery(sqlQuery, list);
       // Assert.assertNotNull(resultSet);

    }
}