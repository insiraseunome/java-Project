package inventory.models;
// Represents the movement table from the MySQL database.
// Contains fields, constructors, getters, and setters.

import java.time.LocalDateTime;

public class Movement {
    private int id;
    private int productId;
    private int userId;
    private String type;
    private int quantity;
    private LocalDateTime date;

    public Movement(int id, int productId, int userId, String type, int quantity, LocalDateTime date) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.type = type;
        this.quantity = quantity;
        this.date = date;
    }

    public int getId(){
        return this.id;
    }

    public int getProductId(){
        return this.productId;
    }

    public int getUserId(){
        return this.userId;
    }

    public String getType(){
        return this.type;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductId(int productId){
        this.productId = productId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
