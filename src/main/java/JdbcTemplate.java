import mapper.RowMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface JdbcTemplate {

    <T> List<T> query(String query, RowMapper<T> rowMapper, Object...args) throws SQLException;

    <T> T queryForObject (String query, RowMapper<T> rowMapper, Object...args);

    int update (String query, Object... args );

    <T> List<T> query(String query, RowMapper<T> rowMapper, Map<String, ?> params);

    <T> T queryForObject(String query, RowMapper<T> rowMapper, Map<String, ?> params);

    int update(String query, Map<String, ?> params);
}
