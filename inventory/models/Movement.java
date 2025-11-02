package inventory.models;
// Represents the movement table from the MySQL database.
// Contains fields, constructors, getters, and setters.

public class Movement {
    private int id;
    private String name;
    private int product_id;
    private int user_id;
    private String contact_info;

    public Movement(int id, String name, int product_id, int user_id, String contact_info) {
        this.id = id;
        this.name = name;
        this.product_id = product_id;
        this.user_id = user_id;
        this.contact_info = contact_info;
    }

    public int getId(int id){
        return this.id;
    }

    public String getName(String name){
        return this.name;
    }

    public int getproduct_id(int product_id){
        return this.product_id;
    }

    public int getuser_id(int user_id){
        return this.user_id;
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

    public void setproduct_id(int product_id){
        this.product_id = product_id;
    }

    public void setuser_id(int user_id){
        this.user_id = user_id;
    }

    public void setContact(String contact_info) {
        this.contact_info = contact_info;
    }
}
