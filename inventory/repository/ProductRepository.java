package inventory.repository;

import inventory.models.Product;
import inventory.exceptions.InventoryException;
import inventory.configs.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    
    public Product create(Product product) {
        String sql = "INSERT INTO Product (name, price, quantity, categoryId, supplierId) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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

            throw new InventoryException("Erro ao criar produto: " + e.getMessage());

        }

    }

    
    public Product getProductById(int id) {
        String sql = "SELECT * FROM Product WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) return null;

            return new Product(

                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getInt("quantity"),
                    rs.getInt("categoryId"),
                    rs.getInt("supplierId")
            );

        } catch (SQLException e) {

            throw new InventoryException("Erro ao buscar produto: " + e.getMessage());

        }

    }

    
    public List<Product> getProductsByName(String name) {
        String sql = "SELECT * FROM Product WHERE name LIKE ?";
        List<Product> products = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                products.add(new Product(

                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getInt("quantity"),
                        rs.getInt("categoryId"),
                        rs.getInt("supplierId")
                ));

            }

            return products;

        } catch (SQLException e) {

            throw new InventoryException("Erro ao buscar produtos por nome: " + e.getMessage());

        }
    }

    
    public boolean update(int id, Product product) {
        String sql = """
            UPDATE Product 
            SET name=?, price=?, quantity=?, categoryId=?, supplierId=? 
            WHERE id=? """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setBigDecimal(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getCategoryId());
            stmt.setInt(5, product.getSupplierId());
            stmt.setInt(6, id);

            int updated = stmt.executeUpdate();
            return updated > 0;

        } catch (SQLException e) {

            throw new InventoryException("Erro ao atualizar produto: " + e.getMessage());

        }
        
    }

}
