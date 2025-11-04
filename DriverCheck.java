import java.sql.DriverManager;
import java.sql.Driver;
import java.util.Enumeration;

public class DriverCheck {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        }

        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            System.out.println("Driver: " + drivers.nextElement().getClass().getName());
        }
    }
}
/*
javac -cp ".;lib/mysql-connector-j-9.5.0.jar" DriverCheck.java
java -cp ".;lib/mysql-connector-j-9.5.0.jar" DriverCheck
*/