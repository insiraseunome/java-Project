package inventory.threads;
// Background thread for syncing inventory data or handling scheduled tasks.
// Can be used to periodically check stock levels, log activity, or trigger automated movements.

import inventory.repository.ProductRepository;
import inventory.models.Product;
import inventory.utils.GeneralUtils;
import java.util.List;



public class InventoryThread implements Runnable {
    
    private int intervalSeconds;

    private ProductRepository productRepo;
    
    public InventoryThread(ProductRepository productRepo, int intervalSeconds) {

        this.productRepo = productRepo;
        this.intervalSeconds = intervalSeconds;

    }
    
    @Override
    public void run() {

        GeneralUtils.log("InventoryThread iniciado. Intervalo: " + intervalSeconds + " segundos.");

        while (true) {

            try {

                Thread.sleep(intervalSeconds * 1000);

                List<Product> products = productRepo.getAllProducts();

                for (Product p : products) {

                    if (p.getQuantity() <=5) {

                        GeneralUtils.log("Estoque baixo: Produto " + p.getName() + " com quantidade " + p.getQuantity());

                    }

                }
   
            }   

        }

    }

}