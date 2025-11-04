package inventory.repository;
// Provides CRUD operations for the user model using JDBC.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inventory.configs.Database;
import inventory.exceptions.InventoryException;
import inventory.models.User;

public class UserRepository {

    public User createUser(User user){
        String query = "INSERT INTO User (name, balance) VALUES (?, ?)";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setBigDecimal(2, user.getBalance());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("User created successfuly: " + user.getName());
            }
        } catch (SQLException e) {
            throw new InventoryException("Failed to create user: ", e);
        }
        return user;
    }

    public User getUserById(int id) {
        String query = "SELECT * FROM User WHERE id = ?";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // build and return user
                return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("balance")
                );
            }
        } catch (SQLException e) {
            throw new InventoryException("Failed to fetch user: ", e);
        }
        return null;
    }

    public List<User> getUsersByName(String name) {
        String query = "SELECT * FROM User WHERE name LIKE ?";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                // return all users when match the name
                users.add(new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("balance")
                ));
                System.out.println("\tID: " + rs.getInt("id"));
                System.out.println("\tName: " + rs.getString("name"));
                System.out.println("\tBalance: " + rs.getBigDecimal("balance"));
                System.out.println();
            }
            return users;
        } catch (SQLException e) {
            throw new InventoryException("Failed to find users: ", e);
        }
    }

    public boolean updateUserById(int id, User user){
        String query = "UPDATE User SET name = ?, balance = ? WHERE id = ?";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setBigDecimal(2, null);
            stmt.setInt(3, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new InventoryException("Failed to update user: ", e);
        }
    }

    public boolean deleteUserById(int id){
        String query = "DELETE FROM User WHERE id = ?";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            System.out.println("User deleted: ID " + id);
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new InventoryException("Failed to delete user: ", e);
        }
    }
}