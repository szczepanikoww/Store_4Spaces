package store;

public class Admin extends User {
    private Inventory inventory;

    public Admin(int AdminID, String AdminName, String AdminPassword) {
        super(AdminID, AdminName, AdminPassword);
    }

    //getters
    public String getLogin(){
        return this.getUserName();
    }

    public String getPassword(){
        return this.getUserPassword();
    }

    //other methods
    public void addProduct(Product product) {
        inventory.addProduct(product);
    }

    public void removeProduct(Product product) {
        inventory.removeProduct(product);
    }

    public void updateProduct(Product product) {
        inventory.updateProduct(product);
    }

    public void manageInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
