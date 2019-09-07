package com.alexa.jdbc.template;

import com.alexa.jdbc.mapper.RowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntityBuilder {
    private final static Logger logger = LoggerFactory.getLogger(EntityBuilder.class);

    public <T> List<T> mapEntities(ResultSet resultSet, RowMapper<T> rowMapper) {
        try {
            List<T> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;
        } catch (SQLException e) {
            logger.error("Failed to process resultSet", e);
        }
        throw new EntityBuilderException("Unable to process resultSet");
    }

    public <T> T mapEntity(ResultSet resultSet, RowMapper<T> rowMapper) {
        try {
            if (resultSet.next()) {
                T result = rowMapper.mapRow(resultSet);
                if (resultSet.next()) {
                    throw new RuntimeException("There is more than one row in resultSet");
                }
                return result;
            } else {
                throw new RuntimeException("ResultSet is empty");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to process resultSet");
        }
    }
}