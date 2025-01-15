package store;

import static org.junit.Assert.*;

public class CustomerTestTest {
    private Customer customer;
    private Customer customer1;
    private Customer customer2;
    private Address address;
    private Address address1;
    private Address address2;

    @org.junit.Before
    public void setUp() throws Exception {
        address = new Address("USA", "Main St", "New York", "NY-123", 58);
        address1 = new Address("USA", "Not main St", "New York", "NY-789", 88);
        address2 = new Address("USA", "Central", "New York", "NY-114", 67);
        customer = new Customer(5678, "Doe", "123", "Don", "Jacks", "don@gmail.com", "456654444", address);
        customer1 = new Customer(468, "mat", "321", "Mateusz", "Gac", "mat@gmail.com", "484331222", address1);
        customer2 = new Customer(789, "mich", "789", "Michal", "Szcz", "mich@gmail.com", "456158777", address2);


    }

    @org.junit.After
    public void tearDown() throws Exception {
    }



    @org.junit.Test
    public void updateCustomer() {
        Store.getCustomers().add(customer);
        Store.getCustomers().add(customer1);
        Store.getCustomers().add(customer2);
        customer.setCustomerName("John");
        customer.setCustomerSurname("Doe");
        customer.setCustomerEmail("super@gmail.com");
        assertEquals(customer.getCustomerName(), "John");
        assertEquals(customer.getCustomerSurname(), "Doe");
        assertEquals(customer.getCustomerEmail(), "super@gmail.com");
    }

    @org.junit.Test
    public void getCustomer() {
        Store.getCustomers().add(customer);
        Store.getCustomers().add(customer1);
        Store.getCustomers().add(customer2);
        assertEquals(Store.getCustomers().get(0), customer);
        assertEquals(Store.getCustomers().get(1), customer1);
        assertEquals(Store.getCustomers().get(2), customer2);
    }

}