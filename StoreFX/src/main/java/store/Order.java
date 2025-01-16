package store;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int OrderID;
    private Customer customer;
    private ArrayList<Product> products;
    private double totalPrice;
    private Date orderDate;

    //Constructor
    public Order(int orderID, Customer customer, ArrayList<Product> products, double totalPrice, Date orderDate) {
        OrderID = orderID;
        this.customer = customer;
        this.products = products;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    //setters
    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    //getters
    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getDetails() {
        return "Order ID: " + OrderID + "\nCustomer: " + customer.getDetails() + "\nTotal Price: " + totalPrice + "\nOrder Date: " + orderDate;
    }

    //other methods
    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void clearCart() {
        products.clear();
    }

    public Object getDate() {
        return orderDate;
    }

    public String getId() {
        return String.valueOf(OrderID);
    }
}
