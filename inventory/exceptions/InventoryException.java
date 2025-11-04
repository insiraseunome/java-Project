package inventory.exceptions;
// Custom exception class for handling inventory-related errors.

public class InventoryException extends RuntimeException {
    public InventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}

