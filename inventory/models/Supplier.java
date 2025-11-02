package inventory.models;
// Represents the supplier table from the MySQL database.
// Contains fields, constructors, getters, and setters.

public class Supplier {
    private int id;
    private String name;
    private String contact_info;

    public Supplier(int id, String name, String contact_info) {
        this.id = id;
        this.name = name;
        this.contact_info = contact_info;
    }

    public int getId(int id){
        return this.id;
    }

    public String getName(String name){
        return this.name;
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

    public void setContact(String contact_info) {
        this.contact_info = contact_info;
    }
}
