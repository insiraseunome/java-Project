package inventory.models;
// Represents the user table from the MySQL database.
// Contains fields, constructors, getters, and setters.

import java.math.BigDecimal;

public class User {
    private int id;
    private String name;
    private BigDecimal balance;

    public User(int id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public BigDecimal getBalance(){
        return this.balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
