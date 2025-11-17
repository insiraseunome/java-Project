package inventory.repository;
// Provides CRUD operations for the category model using JDBC.

import inventory.configs.Database;
import inventory.exceptions.InventoryException;
import inventory.interfaces.CrudRepository;
import inventory.models.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements CrudRepository<Category> {

    public Category  create(Category category) {
        String query = "INSERT INTO Category (name, description) VALUES (?, ?)";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                category.setId(rs.getInt(1));
            }
            System.out.println("Category created successfully: " + category.getName());
            return category;
        } catch (SQLException e) {
            throw new InventoryException("Failed to create category: ", e);
        }
    }

    public Category findById(int id) {
        String query = "SELECT * FROM Category WHERE id = ?";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Category(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description")
                ); 
            }
            return null;
            } catch (SQLException e) {
            throw new InventoryException("Failed to find category: ", e);
        }
    }     

    public List<Category> findAll() {
        String query = "SELECT * FROM Category";
        List<Category> categories = new ArrayList<>();
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categories.add(new Category(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            throw new InventoryException("Failed to list categories: ", e);
        }
        return categories;
    } 

    public boolean update(int id, Category category) {
        String query = "UPDATE Category SET name = ?, description = ? WHERE id = ?";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.setString(2, category.getName());
            stmt.setString(3, category.getDescription());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new InventoryException("Failed to update category: ", e);
        }
    }

    public boolean delete(int id) {
        String query = "DELETE FROM Category WHERE id = ?";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1,id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new InventoryException("Failed to delete category: ", e);
        }
    }
}
