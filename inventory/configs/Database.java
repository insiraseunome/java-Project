package inventory.configs;
// Responsible for establishing the JDBC connection to the MySQL database.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/*******";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "*******";

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("JDBC Connected to MySQL");
            return conn;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
