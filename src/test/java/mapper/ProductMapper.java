package mapper;

import entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
public class ProductMapper implements RowMapper<Product>{
    @Override
    public Product mapRow(ResultSet resultSet) throws SQLException {
        Product products = new Product();
        products.setId(resultSet.getInt("id"));
        products.setName(resultSet.getString("name"));
        products.setCategory(resultSet.getString("category"));
        return products;
    }
}
