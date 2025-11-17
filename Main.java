// Entry point of the application. Initializes the system and orchestrates repositories, models, and threads.

import java.sql.Connection;

import inventory.configs.Database;
import inventory.utils.GeneralUtils;
import inventory.threads.InventoryThread;

import inventory.models.User;
import inventory.models.Product;
import inventory.models.Supplier;
import inventory.models.Category;
import inventory.models.Movement;

import inventory.repository.UserRepository;
import inventory.repository.ProductRepository;
import inventory.repository.SupplierRepository;
import inventory.repository.CategoryRepository;
import inventory.repository.MovementRepository;

public class Main {

    // Private attributes for models
    private User user;
    private Product product;
    private Supplier supplier;
    private Category category;
    private Movement movement;

    // Private attributes for repositories
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private SupplierRepository supplierRepository;
    private CategoryRepository categoryRepository;
    private MovementRepository movementRepository;

    // Private attribute for background thread
    private InventoryThread inventoryThread;

    // Main method (entry point)
    public static void main(String[] args) {

        // TODO: Initialize database connection
        Connection conn = Database.connect();
        if (conn != null) {
            System.out.println("Connected successfuly!");
        } else {
            GeneralUtils.log("Failed to connect.");
        }

        // TODO: Instantiate repositories
        UserRepository userRepo = new UserRepository();
        ProductRepository productRepo = new ProductRepository();
        SupplierRepository supplierRepo = new SupplierRepository();
        CategoryRepository categoryRepo = new CategoryRepository();
        MovementRepository movementRepo = new MovementRepository();

        // TODO: Create sample models (User, Product, etc.)


        // TODO: Start InventoryThread


        // TODO: Simulate operations (CRUD, purchases, movements)

        
    }
}

/*
 javac -cp ".;lib/mysql-connector-j-9.5.0.jar" -d . inventory/configs/Database.java Main.java
 java -cp ".;lib/mysql-connector-j-9.5.0.jar" Main
 */