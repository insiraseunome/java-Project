// Entry point of the application. Initializes the system and tests database connection.

import inventory.configs.Database;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = Database.connect();
        if (conn != null) {
            System.out.println("Connected successfuly!");
        } else {
            System.out.println("Failed to connect.");
        }
    }
}
/*
 * javac -cp ".;lib/mysql-connector-j-9.5.0.jar" -d . inventory/configs/Database.java Main.java
 * java -cp ".;lib/mysql-connector-j-9.5.0.jar" Main
 */