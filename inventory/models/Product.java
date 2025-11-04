package inventory.models;
// Represents the product table from the MySQL database.
// Contains fields, constructors, getters, and setters.
// Represents the Product entity with attributes like id, name, price, and stock quantity.

import java.math.BigDecimal;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private int categoryId;
    private int supplierId;

    public Product(int id, String name, BigDecimal price, int quantity, int categoryId, int supplierId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public BigDecimal getPrice(){
        return this.price;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public int getContact(){
        return this.categoryId;
    }

    public int getSupplier() {
        return this.supplierId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setContact(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setSupplier(int supplierId) {
        this.supplierId = supplierId;
    }
}
