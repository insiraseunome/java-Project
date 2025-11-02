package inventory.models;
// Represents the user table from the MySQL database.
// Contains fields, constructors, getters, and setters.

public class User {
    private int id;
    private String name;
    private Float balance;

    public User(int id, String name, Float balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public int getId(int id){
        return this.id;
    }

    public String getName(String name){
        return this.name;
    }

    public Float getBalance(Float balance){
        return this.balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}
