package mapper;

import entity.Products;

import java.sql.ResultSet;
import java.sql.SQLException;
public class ProductMapper implements RowMapper<Products>{
    @Override
    public Products mapRow(ResultSet resultSet) throws SQLException {
        Products products = new Products();
        products.setId(resultSet.getInt("id"));
        products.setName(resultSet.getString("name"));
        products.setCategory(resultSet.getString("category"));
        return products;
    }
}
