package inventory.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import inventory.configs.Database;
import inventory.exceptions.InventoryException;
import inventory.interfaces.CrudRepository;
import inventory.models.Movement;

public class MovementRepository implements CrudRepository<Movement> {

    
    public Movement create(Movement movement) {
        String query = "INSERT INTO Movement (productId, userId, type, quantity, date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
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

            return movement;

        } catch (SQLException e) {

            throw new InventoryException("Error inserting movement: " + e.getMessage());

        }

    }

   
    public Movement findById(int id) {
        String query = "SELECT * FROM Movement WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Movement(
                    rs.getInt("id"),
                    rs.getInt("productId"),
                    rs.getInt("userId"),
                    rs.getString("type"),
                    rs.getInt("quantity"),
                    rs.getObject("date", LocalDateTime.class)
                );

            }

            return null;

        } catch (SQLException e) {

            throw new InventoryException("Error retrieving movement: " + e.getMessage());

        }

    }

   
    public List<Movement> findAll() {
        List<Movement> list = new ArrayList<>();
        String query = "SELECT * FROM Movement ORDER BY date DESC";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Movement(

                    rs.getInt("id"),
                    rs.getInt("productId"),
                    rs.getInt("userId"),
                    rs.getString("type"),
                    rs.getInt("quantity"),
                    rs.getObject("date", LocalDateTime.class)
                ));

            }

            return list;

        } catch (SQLException e) {

            throw new InventoryException("Error retrieving movements: " + e.getMessage());

        }

    }

   
    public boolean update(int id, Movement movement) {
        String query = "UPDATE Movement SET productId = ?, userId = ?, type = ?, quantity = ?, date = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, movement.getProductId());
            stmt.setInt(2, movement.getUserId());
            stmt.setString(3, movement.getType());
            stmt.setInt(4, movement.getQuantity());
            stmt.setObject(5, movement.getDate());
            stmt.setInt(6, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            throw new InventoryException("Error updating movement: " + e.getMessage());

        }

    }

 
    public boolean delete(int id) {
        String query = "DELETE FROM Movement WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            throw new InventoryException("Error deleting movement: " + e.getMessage());

        }

    }

}
