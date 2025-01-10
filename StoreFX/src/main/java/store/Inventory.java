package store;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> productsList = new ArrayList<>();

    public void addProduct(Product product) {
        productsList.add(product);
    }

    public void removeProduct(Product product) {
        productsList.remove(product);
    }

    public ArrayList<Product> getProducts() {
        return productsList;
    }

    public void updateProductQuantity(Product product, int i) {
        for (Product p : productsList) {
            if (p.getProductName() == product.getProductName()) {
                p.setProductQuantity(p.getQuantity() + i);
            }
        }
    }

//    public void updateProduct(Product product) {
//        for (Product p : productsList) {
//            if (p.getProductID() == product.getProductID()) {
//                p.setProductName(product.getProductName());
//                p.setProductPrice(product.getPrice());
//                p.setProductQuantity(product.getQuantity());
//            }
//        }
//    }

}
