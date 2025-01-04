package store;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> productsList;

    public Inventory() {
        productsList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productsList.add(product);
    }

    public void removeProduct(Product product) {
        productsList.remove(product);
    }

    public void updateProduct(Product product) {
        for (Product p : productsList) {
            if (p.getProductID() == product.getProductID()) {
                p.setProductName(product.getProductName());
                p.setProductPrice(product.getProductPrice());
                p.setProductQuantity(product.getProductQuantity());
            }
        }
    }
}
