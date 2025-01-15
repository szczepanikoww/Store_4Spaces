package store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> productsInCart;
    private double totalPrice;

    public Cart() {
        productsInCart = new HashMap<Product, Integer>();
        this.totalPrice = 0;
    }

    public void addProduct(Product product, int quantity) {
        if(productsInCart.containsKey(product)){
            productsInCart.put(product, productsInCart.get(product) + quantity);
        } else {
            productsInCart.put(product, quantity);
        }
    }

    public void removeProduct(Product product, int quantity) {
        if(productsInCart.containsKey(product)){
            if(productsInCart.get(product) > quantity){
                productsInCart.put(product, productsInCart.get(product) - quantity);
            } else {
                productsInCart.remove(product);
            }
        }
    }

    public double getTotalPrice() {
        for (Map.Entry<Product, Integer> entry : productsInCart.entrySet()) {
            totalPrice += getProductPrice(entry.getKey()) * entry.getValue();
        }
        return totalPrice;
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<>(productsInCart.keySet());
    }

    public int getProductQuantity(Product product) {
        return productsInCart.getOrDefault(product, 0);
    }

    public void clearCart() {
        productsInCart.clear();
    }

    public double getProductPrice(Product product) {
        return product.getPrice();
    }

    public Map<Product, Integer> getProductsInCart() {
        return productsInCart;
    }

    public void updateProductQuantity(Product product, int delta) {
        if (productsInCart.containsKey(product)) {
            productsInCart.put(product, productsInCart.get(product) + delta);
        }
    }


    public double updateTotalPrice() {
        double totalPrice = 0.0;
        for (Map.Entry<Product, Integer> entry : productsInCart.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        this.totalPrice = totalPrice;
        return this.totalPrice;
    }

}
