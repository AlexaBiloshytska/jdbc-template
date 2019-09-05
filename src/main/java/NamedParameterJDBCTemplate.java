import executor.QueryExecutor;
import mapper.RowMapper;
import parser.Parser;
import template.EntityBuilder;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NamedParameterJDBCTemplate implements JdbcTemplate {
    private QueryExecutor queryExecutor;
    private EntityBuilder entityBuilder;
    private Parser parser;

    public NamedParameterJDBCTemplate(DataSource dataSource) {

        this.queryExecutor = new QueryExecutor(dataSource);
        this.entityBuilder = new EntityBuilder();
        this.parser = new Parser();
    }

    @Override
    public <T> List<T> query(String query, RowMapper<T> rowMapper, Object... args) {
        List<Object> list = Arrays.asList(args);
        return queryExecutor.executeQuery(query, rowMapper, list);
    }

    @Override
    public <T> T queryForObject(String query, RowMapper<T> rowMapper, Object... args) {
        List<Object> list = Arrays.asList(args);
        return queryExecutor.executeQueryForObject(query, rowMapper, list);
    }

    @Override
    public int update(String query, Object... args) {
        List<Object> list = Arrays.asList(args);
        return queryExecutor.executeUpdate(query, list);
    }

    @Override
    public <T> List<T> query(String query, RowMapper<T> rowMapper, Map<String, ?> params) {
        String placeHolderQuery = parser.getPlaceHolderQuery(query, params);
        List<?> orderParamList = parser.getOrderParamList(query, params);
        return queryExecutor.executeQuery(placeHolderQuery, rowMapper, orderParamList);
    }

    @Override
    public int update(String query, Map<String, ?> params) {
        String placeHolderQuery = parser.getPlaceHolderQuery(query, params);
        List<?> orderParamList = parser.getOrderParamList(query, params);
        return queryExecutor.executeUpdate(placeHolderQuery,orderParamList);
    }

    @Override
    public <T> T queryForObject(String query, RowMapper<T> rowMapper, Map<String, ?> params) {
        String placeHolderQuery = parser.getPlaceHolderQuery(query, params);
        List<?> orderParamList = parser.getOrderParamList(query, params);
        return queryExecutor.executeQueryForObject(placeHolderQuery, rowMapper, orderParamList);
    }
}
