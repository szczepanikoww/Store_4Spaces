package store;

import java.util.ArrayList;
import java.util.Collection;

public class Store {
    private static ArrayList<Admin> admins = new ArrayList<Admin>();
    private static ArrayList<Customer> customers = new ArrayList<Customer>();
    private static Inventory inventory;
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Invoice> invoices = new ArrayList<Invoice>();


        public Store(){
            Admin admin = new Admin(1, "admin", "admin");
            admins.add(admin);
            this.inventory = new Inventory();
            this.orders = new ArrayList<Order>();
            this.invoices = new ArrayList<Invoice>();
        }

//    static{
//        Admin admin = new Admin(1, "admin", "admin");
//        admins.add(admin);
//    }

    //Constructor
    public Store(ArrayList<Admin> admins, ArrayList<Customer> customers, Inventory inventory, ArrayList<Order> orders, ArrayList<Invoice> invoices, ArrayList<Product> products) {
        this.admins = admins;
        this.customers = customers;
        this.inventory = inventory;
        this.orders = orders;
        this.invoices = invoices;
    }

    //getters
    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }


    public Inventory getInventory() {
        return inventory;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public ArrayList<Product> getProducts() {
        return inventory.getProducts();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Store!");
        System.out.println(inventory.getProducts());
    }

    //myślałem, żeby dodać to co było w projekcie z Samochodami czyli to, żeby dodać tych listenerów itp ale nie wiem czy to będzie nam potrzebne
}
