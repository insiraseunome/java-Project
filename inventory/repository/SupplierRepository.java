package inventory.repository;

import inventory.models.Supplier;
import inventory.exceptions.InventoryException;
import inventory.interfaces.CrudRepository;
import inventory.configs.Database;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SupplierRepository implements CrudRepository<Supplier>{

    public Supplier create(Supplier supplier) {
        String query = "INSERT INTO Supplier (name, contact_info) VALUES (?, ?)";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getContact());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                supplier.setId(rs.getInt(1));
            }
            System.out.println("Supplier created successfully: " + supplier.getName());
            return supplier;
        } catch (SQLException e) {
            throw new InventoryException("Failed to create supplier: ", e);
        }
    }
    
    public Supplier findById(int id) {
        String query = "SELECT * FROM Supplier WHERE id = ?";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) return null;
            return new Supplier(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("contact_info")
            );
        } catch (SQLException e) {
            throw new InventoryException("Failed to find supplier: ", e);
        }
    }

    public List<Supplier> findAll() {
        String query = "SELECT * FROM Supplier";
        List<Supplier> suppliers = new ArrayList<>();
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                suppliers.add(new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contact_info")
                ));
            }
            return suppliers;
        } catch (SQLException e) {
            throw new InventoryException("Failed to list suppliers: ", e);
        }
    }

    public boolean update(int id, Supplier supplier) {
        String query = "UPDATE Supplier SET name = ?, contact_info = ? WHERE id = ?";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getContact());
            stmt.setInt(3, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new InventoryException("Failed to update supplier: ", e);
        }
    }
   
    public boolean delete(int id) {
        String query = "DELETE FROM Supplier WHERE id = ?";
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new InventoryException("Failed to delete supplier: ", e);
        }
    }

    public List<Supplier> filterByName(String name) {
        String query = "SELECT * FROM Supplier WHERE name LIKE ?";
        List<Supplier> suppliers = new ArrayList<>();
        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                suppliers.add(new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contact_info")
                ));
            }
            return suppliers;
        } catch (SQLException e) {
            throw new InventoryException("Failed to filter suppliers by name: ", e);
        }
    }
}
