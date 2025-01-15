package store;

import static org.junit.Assert.*;

public class CartTestTest {
    private Cart cart;
    private Product product1;
    private Product product2;

    @org.junit.Before
    public void setUp() throws Exception {
        cart = new Cart();
        product1 = new Product( "product1", 1000, 10, "Samsung");
        product2 = new Product("product2", 2000, 20, "Apple");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void addProduct() {
        cart.addProduct(product1,10);
        cart.addProduct(product2,15);
        assertEquals(cart.getProducts().size(), 2);
    }

    @org.junit.Test
    public void removeProduct() {
        cart.addProduct(product1,10);
        cart.addProduct(product2,15);
        cart.removeProduct(product1,10);
        assertEquals(cart.getProducts().size(), 1);
    }

    @org.junit.Test
    public void getTotalPrice() {
        cart.addProduct(product1,1);
        cart.addProduct(product2,2);
        assertEquals(cart.getTotalPrice(), 5000,1);
    }

    @org.junit.Test
    public void getProductQuantity() {
        cart.addProduct(product1, 10);
        cart.addProduct(product2,15);
        assertEquals(cart.getProductQuantity(product1), 10);
        assertEquals(cart.getProductQuantity(product2), 15);
    }

}