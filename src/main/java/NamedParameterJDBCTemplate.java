import com.zaxxer.hikari.HikariDataSource;
import executor.QueryExecutor;
import mapper.RowMapper;
import template.EntityBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NamedParameterJDBCTemplate implements JdbcTemplate {
    private DataSource dataSource;
    private RowMapper rowMapper;

    public NamedParameterJDBCTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * @param query - sql query with placeholders
     * @param rowMapper  - map row to object
     * @param args - placeholder parameters
     * @return List<T> - Objects list which mapped with resultSet;
     */
    @Override
    public <T> List<T> query(String query, RowMapper<T> rowMapper, Object... args) {
            QueryExecutor queryExecutor = new QueryExecutor(dataSource);
            List<Object> params = Arrays.asList(args);
            List<T> resultSet = queryExecutor.executeQuery(query, params,rowMapper);
            return resultSet;
    }

    @Override
    public <T> T queryForObject(String query, RowMapper<T> rowMapper, Object... args) {
        return null;
    }

    @Override
    public int update(String query, Object... args) {
        return 0;
    }

    @Override
    public <T> List<T> query(String query, RowMapper<T> rowMapper, Map<String, ?> params) {
        return null;
    }

    @Override
    public int update(String query, Map<String, ?> params) {
        return 0;
    }

    @Override
    public <T> T queryForObject(String query, RowMapper<T> rowMapper, Map<String, ?> params) {
        return null;
    }
}
