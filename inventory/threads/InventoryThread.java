package inventory.threads;

import inventory.repository.ProductRepository;
import inventory.models.Product;
import inventory.utils.GeneralUtils;
import java.util.List;

public class InventoryThread implements Runnable {
    
    private int checkIntervalSeconds;
    private ProductRepository productRepository;
    private boolean isRunning = true;

    public InventoryThread(ProductRepository productRepository, int checkIntervalSeconds, boolean isRunning) {
        this.productRepository = productRepository;
        this.checkIntervalSeconds = checkIntervalSeconds;
        this.isRunning = isRunning;
    }

    public void stop() {
        isRunning = false;
    }
    
    @Override
    public void run() {
        GeneralUtils.log("InventoryThread initialized. Interval: " + checkIntervalSeconds + " seconds.");
        while (isRunning) {
            try {
                Thread.sleep(checkIntervalSeconds * 1000);
                List<Product> products = productRepository.findAll();
                for (Product p : products) {
                    if (p.getQuantity() <= 5) {
                        GeneralUtils.log("Low stock: Product " + p.getName() + "\t Quantity: " + p.getQuantity());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                stop();
            }
        }
    }
}
