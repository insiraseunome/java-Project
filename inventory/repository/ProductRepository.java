package inventory.repository;

import inventory.models.Product;
import inventory.exceptions.InventoryException;
import inventory.interfaces.CrudRepository;
import inventory.configs.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements CrudRepository<Product>{
    
    public Product create(Product product) {
        String query = """
                        INSERT INTO Product (name, price, quantity, category_id, supplier_id)
                        VALUES (?, ?, ?, ?, ?)
                       """;
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setBigDecimal(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getCategoryId());
            stmt.setInt(5, product.getSupplierId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                product.setId(rs.getInt(1));
            }
            return product;
        } catch (SQLException e) {
            throw new InventoryException("Failed to create product: ", e);
        }
    }
    
    @Override
    public Product findById(int id) {
        String query = "SELECT * FROM Product WHERE id = ?";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getInt("quantity"),
                    rs.getInt("category_id"),
                    rs.getInt("supplier_id")
                );
            }
        } catch (SQLException e) {
            throw new InventoryException("Failed to find product: ", e);
        }
        return null;
    }
    
    public List<Product> getProductsByName(String name) {
        String query = "SELECT * FROM Product WHERE name LIKE ?";
        List<Product> products = new ArrayList<>();
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getInt("quantity"),
                    rs.getInt("category_id"),
                    rs.getInt("supplier_id")
                ));
            }
            return products;
        } catch (SQLException e) {
            throw new InventoryException("Failed to search all products: ", e);
        }
    }

    public List<Product> findAll() {
        String query = "SELECT * FROM Product";
        List<Product> products = new ArrayList<>();
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getInt("quantity"),
                    rs.getInt("category_id"),
                    rs.getInt("supplier_id")
                ));
            }
        } catch (SQLException e) {
            throw new InventoryException("Failed to list products: ", e);
        }
        return products;
    }

    public boolean update(int id, Product product) {
        String query = """
                        UPDATE Product 
                        SET name=?, price=?, quantity=?, category_id=?, supplier_id=? 
                        WHERE id=?
                       """;
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setBigDecimal(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getCategoryId());
            stmt.setInt(5, product.getSupplierId());
            stmt.setInt(6, id);
            int updated = stmt.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            throw new InventoryException("Failed to update product: ", e);
        }
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Product WHERE id = ?";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new InventoryException("Failed to delete product: ", e);
        }
    }
}