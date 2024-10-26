package com.example.University.Dao;

import com.example.University.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDaoImpl implements ProductDao {

  private final JdbcTemplate jdbcTemplate;

  public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Product> getAll() {
    String sql = "SELECT * FROM product";  // Assuming 'product' is the table name
    return jdbcTemplate.query(sql, this::mapRowToProduct);
  }

  @Override
  public Product getProductById(Long id) {
    String sql = "SELECT * FROM product WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, this::mapRowToAllProduct, id); // Pass id as a parameter
  }

  private Product mapRowToAllProduct(ResultSet rs, int rowNum) throws SQLException {
    Product product = new Product();
    product.setId(rs.getLong("id"));
    product.setProductName(rs.getString("product_name"));
    product.setProductCode(rs.getLong("product_code"));
    product.setProductPrice(rs.getDouble("product_price"));

    String productDescriptionJson = rs.getString("product_description");
    if (productDescriptionJson != null && !productDescriptionJson.isEmpty()) {
      ObjectMapper objectMapper = new ObjectMapper();
      try {
        Map<String, Object> productDescription = objectMapper.readValue(
          productDescriptionJson, new TypeReference<Map<String, Object>>() {});
        product.setProductDescription(productDescription);
      } catch (Exception e) {
        throw new RuntimeException("Error parsing JSON for productDescription", e);
      }
    }

    product.setImage(rs.getString("image"));
    product.setRatings(rs.getDouble("ratings"));

    return product;
  }

  private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
    Product product = new Product();
    product.setId(rs.getLong("id"));
    product.setProductName(rs.getString("product_name"));
    product.setProductCode(rs.getLong("product_code"));
    product.setProductPrice(rs.getDouble("product_price"));

    String productDescriptionJson = rs.getString("product_description");
    if (productDescriptionJson != null && !productDescriptionJson.isEmpty()) {
      ObjectMapper objectMapper = new ObjectMapper();
      try {
        Map<String, Object> productDescription = objectMapper.readValue(
          productDescriptionJson, new TypeReference<Map<String, Object>>() {});
        product.setProductDescription(productDescription);
      } catch (Exception e) {
        throw new RuntimeException("Error parsing JSON for productDescription", e);
      }
    }

    product.setImage(rs.getString("image"));
    product.setRatings(rs.getDouble("ratings"));

    return product;
  }

}
