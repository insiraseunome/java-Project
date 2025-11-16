package inventory.repository;

import inventory.models.Supplier;
import inventory.exceptions.InventoryException;
import inventory.configs.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class SupplierRepository {

    
    public Supplier create(Supplier supplier) {
        String sql = "INSERT INTO Supplier (name, contactInfo) VALUES (?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getContact());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                supplier.setId(rs.getInt(1));
            }

            return supplier;

        } catch (SQLException e) {

            throw new InventoryException("Erro ao criar fornecedor: " + e.getMessage());

        }

    }

    
    public Supplier findById(int id) {
        String sql = "SELECT * FROM Supplier WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) return null;

            return new Supplier(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("contactInfo")
            );

        } catch (SQLException e) {

            throw new InventoryException("Erro ao buscar fornecedor por ID: " + e.getMessage());

        }

    }


    public List<Supplier> findAll() {
        String sql = "SELECT * FROM Supplier";
        List<Supplier> suppliers = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                suppliers.add(new Supplier(

                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contactInfo")
                ));

            }

            return suppliers;

        } catch (SQLException e) {

            throw new InventoryException("Erro ao listar fornecedores: " + e.getMessage());

        }

    }

    
    public boolean update(int id, Supplier supplier) {
        String sql = "UPDATE Supplier SET name = ?, contactInfo = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getContact());
            stmt.setInt(3, id);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {

            throw new InventoryException("Erro ao atualizar fornecedor: " + e.getMessage());

        }

    }

   
    public boolean delete(int id) {
        String sql = "DELETE FROM Supplier WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {

            throw new InventoryException("Erro ao deletar fornecedor: " + e.getMessage());

        }

    }

    
    public List<Supplier> filterByName(String name) {
        String sql = "SELECT * FROM Supplier WHERE name LIKE ?";
        List<Supplier> suppliers = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                suppliers.add(new Supplier(

                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contactInfo")
                ));

            }

            return suppliers;

        } catch (SQLException e) {

            throw new InventoryException("Erro ao filtrar fornecedores por nome: " + e.getMessage());

        }

    }

    
    public List<Supplier> filterByPriceRange(BigDecimal min, BigDecimal max) {
        return new ArrayList<>(); 

    }

}
