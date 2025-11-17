// Entry point of the application. Initializes the system and orchestrates repositories, models, and threads.

import inventory.configs.Database;
import inventory.utils.GeneralUtils;
import inventory.threads.InventoryThread;
import inventory.models.User;
import inventory.models.Supplier;
import inventory.models.Category;
import inventory.models.Product;
import inventory.models.Movement;
import inventory.repository.UserRepository;
import inventory.repository.SupplierRepository;
import inventory.repository.CategoryRepository;
import inventory.repository.ProductRepository;
import inventory.repository.MovementRepository;

import java.math.BigDecimal;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        // Initialize database connection
        Connection conn = Database.connect();
        if (conn != null) {
            GeneralUtils.log("Connected successfully!");
        } else {
            GeneralUtils.log("Failed to connect.");
        }

        // Instantiate repositories
        UserRepository userRepo = new UserRepository();
        SupplierRepository supplierRepo = new SupplierRepository();
        CategoryRepository categoryRepo = new CategoryRepository();
        ProductRepository productRepo = new ProductRepository();
        MovementRepository movementRepo = new MovementRepository();

        // Create sample users
        User user1 = userRepo.create(new User(0, "Elias", new BigDecimal("100.00")));
        User user2 = userRepo.create(new User(0, "Rafa", new BigDecimal("110.00")));
        User user3 = userRepo.create(new User(0, "Rodri", new BigDecimal("110.00")));
        User user4 = userRepo.create(new User(0, "Davi", new BigDecimal("100.00")));

        // Create products using generic method
        Product caseXiaomi = createProduct("Xiaomi", "Redmi Note 15", new BigDecimal("20.00"), 35, supplierRepo, categoryRepo, productRepo);
        Product caseSamsung = createProduct("Samsung", "Galaxy S25", new BigDecimal("25.00"), 30, supplierRepo, categoryRepo, productRepo);
        Product caseApple = createProduct("Apple", "iPhone 17 Pro Max", new BigDecimal("50.00"), 20, supplierRepo, categoryRepo, productRepo);
        Product caseMotorola = createProduct("Motorola", "Edge 50", new BigDecimal("15.00"), 25, supplierRepo, categoryRepo, productRepo);

        // Start InventoryThread (monitor stock every 5 seconds)
        new Thread(new InventoryThread(productRepo, 5, true)).start();

        // Simulate purchases
        simulatePurchase(user1, caseXiaomi, 2, userRepo, productRepo, movementRepo);
        simulatePurchase(user2, caseSamsung, 3, userRepo, productRepo, movementRepo);
        simulatePurchase(user3, caseApple, 1, userRepo, productRepo, movementRepo);
        simulatePurchase(user4, caseMotorola, 4, userRepo, productRepo, movementRepo);
    }

    // Generic product creation method
    private static Product createProduct(String brand, String model, BigDecimal price, int quantity,
                                         SupplierRepository supplierRepo, CategoryRepository categoryRepo,
                                         ProductRepository productRepo) {
        Supplier supplier = supplierRepo.create(new Supplier(0, brand, "contact@" + brand.toLowerCase() + ".com"));
        Category category = categoryRepo.create(new Category(0, brand, brand + " phone cases"));
        Product product = new Product(0, brand + " " + model + " Case", price, quantity, category.getId(), supplier.getId());
        product = productRepo.create(product);

        return product;
    }

    // Simulate purchase operation
    private static void simulatePurchase(User user, Product product, int quantity,
                                         UserRepository userRepo, ProductRepository productRepo,
                                         MovementRepository movementRepo) {
        BigDecimal totalPrice = product.getPrice().multiply(new BigDecimal(quantity));

        if (user.getBalance().compareTo(totalPrice) >= 0 && product.getQuantity() >= quantity) {
            user.setBalance(user.getBalance().subtract(totalPrice));
            product.setQuantity(product.getQuantity() - quantity);

            userRepo.update(user.getId(), user);
            productRepo.update(product.getId(), product);

            Movement movement = new Movement(0, product.getId(), user.getId(), "BUY", quantity, java.time.LocalDateTime.now());
            movementRepo.create(movement);

            GeneralUtils.log("Purchase successful: " + user.getName() + " bought " + quantity + " " + product.getName());
        } else {
            GeneralUtils.log("Purchase failed for " + user.getName() + ": insufficient balance or stock.");
        }
    }
}

/*
javac -cp ".;lib/mysql-connector-j-9.5.0.jar" -d . inventory/configs/*.java inventory/exceptions/*.java inventory/interfaces/*.java inventory/models/*.java inventory/repository/*.java inventory/threads/*.java Main.java
java -cp ".;lib/mysql-connector-j-9.5.0.jar" Main
 */
