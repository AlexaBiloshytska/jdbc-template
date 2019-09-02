package executor;

import mapper.RowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import template.EntityBuilder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryExecutor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private DataSource dataSource;
    private EntityBuilder entityBuilder = new EntityBuilder();

    public QueryExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> executeQuery(String query, List<?> param, RowMapper<T> rowMapper) {
        long startExecution = System.currentTimeMillis();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setStatementVariables(statement, param);

            logger.info("SQL: {}", statement);
            ResultSet resultSet = statement.executeQuery();
            List<T> entities = entityBuilder.mapEntities(resultSet, rowMapper);

            logger.debug("Statement executed in {} milliseconds", System.currentTimeMillis() - startExecution);

            return entities;

        } catch (SQLException e) {
            logger.error("SQL Failed: {}", query);
            throw new RuntimeException(e);
        }
    }

    public int executeUpdate(String query, List<?> params) {
        long startExecution = System.currentTimeMillis();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            setStatementVariables(statement, params);

            logger.info("SQL: {}", statement);
            int affectedRows = statement.executeUpdate();
            logger.debug("Statement executed in {} milliseconds", System.currentTimeMillis() - startExecution);

            return affectedRows;

        } catch (SQLException e) {
            logger.debug("Statement executed in {} milliseconds", System.currentTimeMillis() - startExecution);
            throw new RuntimeException(e);
        }
    }

    private void setStatementVariables(PreparedStatement statement, List<?> params) {
        try {
            int index = 1;
            // for (int i = 0; i< param.size; i++) { param.get(i)}

            for (Object param : params) {
                Class paramClass = param.getClass();

                if (Boolean.class.equals(paramClass)) {
                    statement.setBoolean(index, (boolean) param);

                } else if (Integer.class.equals(paramClass)) {
                    statement.setInt(index, (int) param);

                } else if (Double.class.equals(paramClass)) {
                    statement.setDouble(index, (double) param);

                } else if (Long.class.equals(paramClass)) {
                    statement.setLong(index, (long) param);

                } else if (Float.class.equals(paramClass)) {
                    statement.setFloat(index, (float) param);

                } else if (Date.class.equals(paramClass)) {
                    statement.setDate(index, (Date) param);

                } else {
                    statement.setString(index, (String) param);
                }
                index++;
            }
        } catch (SQLException e) {
            logger.error("Failed to set statement parameters: {}", statement);
            throw new RuntimeException(e);
        }
    }
}

