package inventory.repository;
// Provides CRUD operations for the category model using JDBC.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import inventory.configs.Database;
import inventory.exceptions.InventoryException;
import inventory.interfaces.CrudRepository;
import inventory.models.Category;
import inventory.models.Movement;



public class CategoryRepository implements CrudRepository<Category>{

    //criar
    public Category  create(Category category) {

        String query = "INSERT INTO Category (name, description) VALUES (?, ?)";

        try (Connection conn = Database.getConnection();

        PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, category.getName());
            stmt.setString(1, category.getDescription());
            stmt.executeUpdate():

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {

                category.setId(rs.getInt(1));

            }

            return category;

        } catch (SQLDataException e) {

            throw new InventoryException(" Erro ao cria categoria: " + e.getMessage());

        }

    }

}


    //pesquisar
    public Category getByid(int id) {

        String query = "SELECT * FROM Category WHERE id = ?";

        try (Connection conn = Database.getConnection();

             PreparedStatement stmt = conn.prepareStatement(query)){

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

                throw new InventoryException(" Failed to fetch category: " + e.getMessage());

             }

    //lista
    public List<Category> getAll() {

        String query = "SELECT * FROM Category";

        List<Category> categories = new ArrayList<>();

        try (Connection conn = Database.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                categories.add(new Category(

                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description")
                ));

            }

        }

    } catch (SQLException e) {

        throw new InventoryException("Failed to find category: " + e.getMessage());

    }

    return categories;

    //update
    public boolean update(Category category) {

        String query = "UPDATE Category SET name = ?, description = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setInt(3, category.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            throw new InventoryException("Failed to update category: " + e.getMessage());

        }

    }

    //Deletar
    public boolean delete(int id) {

        String query = "DELETE FROM Category WHERE id = ?";

        try (Connection conn= Database.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1,id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            throw new InventoryException("Failed to delete category: " + e.getMessage());
        }

    }
    
    }
