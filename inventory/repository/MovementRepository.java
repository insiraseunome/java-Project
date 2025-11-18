package inventory.repository;

import inventory.configs.Database;
import inventory.exceptions.InventoryException;
import inventory.interfaces.CrudRepository;
import inventory.models.Movement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovementRepository implements CrudRepository<Movement> {

    @Override
    public Movement create(Movement movement) {
        String query = """
                        INSERT INTO Movement (product_id, user_id, type, quantity, date)
                        VALUES (?, ?, ?, ?, ?)
                       """;
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, movement.getProductId());
            stmt.setInt(2, movement.getUserId());
            stmt.setString(3, movement.getType());
            stmt.setInt(4, movement.getQuantity());
            stmt.setObject(5, movement.getDate());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                movement.setId(rs.getInt(1));
            }
            System.out.println("Movement created successfully: " + movement.getId());
            return movement;
        } catch (SQLException e) {
            throw new InventoryException("Failed to create movement: " + e.getMessage(), e);
        }
    }

    @Override
    public Movement findById(int id) {
        String query = "SELECT * FROM Movement WHERE id = ?";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Movement(
                    rs.getInt("id"),
                    rs.getInt("product_id"),
                    rs.getInt("user_id"),
                    rs.getString("type"),
                    rs.getInt("quantity"),
                    rs.getObject("date", LocalDateTime.class)
                );
            }
            return null;
        } catch (SQLException e) {
            throw new InventoryException("Failed to find movement: " + e.getMessage(), e);
        }
    }

    public List<Movement> findAll() {
        List<Movement> list = new ArrayList<>();
        String query = "SELECT * FROM Movement ORDER BY date DESC";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Movement(
                    rs.getInt("id"),
                    rs.getInt("product_id"),
                    rs.getInt("user_id"),
                    rs.getString("type"),
                    rs.getInt("quantity"),
                    rs.getObject("date", LocalDateTime.class)
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new InventoryException("Failed to list movements: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean update(int id, Movement movement) {
        String query = """
            UPDATE Movement
            SET product_id = ?, user_id = ?, type = ?, quantity = ?, date = ?
            WHERE id = ?
        """;
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, movement.getProductId());
            stmt.setInt(2, movement.getUserId());
            stmt.setString(3, movement.getType());
            stmt.setInt(4, movement.getQuantity());
            stmt.setObject(5, movement.getDate());
            stmt.setInt(6, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new InventoryException("Failed to update movement: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Movement WHERE id = ?";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new InventoryException("Failed to delete movement: " + e.getMessage(), e);
        }
    }
}
