package inventory.models;
// Represents the supplier table from the MySQL database.
// Contains fields, constructors, getters, and setters.

public class Supplier {
    private int id;
    private String name;
    private String contactInfo;

    public Supplier(int id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getContact(){
        return this.contactInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
