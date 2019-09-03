import com.zaxxer.hikari.HikariDataSource;
import executor.QueryExecutor;
import mapper.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NamedParameterJDBCTemplate implements JdbcTemplate {
    private QueryExecutor queryExecutor;

    public NamedParameterJDBCTemplate(DataSource dataSource) {
        this.queryExecutor = new QueryExecutor(dataSource);
    }

    @Override
    public <T> List<T> query(String query, RowMapper<T> rowMapper, Object... args) {
        return queryExecutor.executeQuery(query, rowMapper, args);
    }

    @Override
    public <T> T queryForObject(String query, RowMapper<T> rowMapper, Object... args) {
        return queryExecutor.executeQueryForObject(query, rowMapper, args);
    }

    @Override
    public int update(String query, Object... args) {
        return queryExecutor.executeUpdate(query, args);
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
