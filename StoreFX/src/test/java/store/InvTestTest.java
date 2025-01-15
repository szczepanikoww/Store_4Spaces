package store;

import static org.junit.Assert.*;

public class InvTestTest {
    private Inventory inventory;
    private Product product1;
    private Product product2;


    @org.junit.Before
    public void setUp() throws Exception {
        inventory = new Inventory();
        product1 = new Product( "product1", 1000, 10, "Samsung");
        product2 = new Product("product2", 2000, 20, "Apple");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void addProduct() {
        inventory.addProduct(product1);
        inventory.addProduct(product2);
        assertEquals(inventory.getProducts().size(), 2);
    }

    @org.junit.Test
    public void removeProduct() {
        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.removeProduct(product1);
        assertEquals(inventory.getProducts().size(), 1);
    }

}