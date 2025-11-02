package inventory.models;
// Represents the category table from the MySQL database.
// Contains fields, constructors, getters, and setters.

public class Category {
    private int id;
    private String name;
    private String description;

    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId(int id){
        return this.id;
    }

    public String getName(String name){
        return this.name;
    }

    public String getDescription(String description){
        return this.description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
