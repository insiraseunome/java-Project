package inventory.models;
// Represents the product table from the MySQL database.
// Contains fields, constructors, getters, and setters.
// Represents the Product entity with attributes like id, name, price, and stock quantity.

public class Product {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private String contact_info;

    public Product(int id, String name, int price, int quantity, String contact_info) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.contact_info = contact_info;
    }

    public int getId(int id){
        return this.id;
    }

    public String getName(String name){
        return this.name;
    }

    public int getPrice(int price){
        return this.price;
    }

    public int getQuantity(int quantity){
        return this.quantity;
    }

    public String getContact(String contact_info){
        return this.contact_info;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setContact(String contact_info) {
        this.contact_info = contact_info;
    }
}
