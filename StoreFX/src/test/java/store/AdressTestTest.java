package store;

import static org.junit.Assert.*;

public class AdressTestTest {
    private Address address;
    private Address address1;
    private Address address2;

    @org.junit.Before
    public void setUp() throws Exception {
        address = new Address("Polska",  "Kwiatowa", "Gdansk", "34-500",80);
        address1 = new Address("Polska",  "Ogrodowa", "Krakow", "34-526",9);
        address2 = new Address("Polska",  "Miodowa", "Warszawa", "34-969", 4);

    }
    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getCountry() {
        assertEquals(address.getCountry(), "Polska");
        address1.setCountry("Niemcy");
        assertEquals(address1.getCountry(), "Niemcy");
        assertEquals(address2.getCountry(), "Polska");
    }

    @org.junit.Test
    public void getStreet() {
        assertEquals(address.getStreet(), "Kwiatowa");
        assertEquals(address1.getStreet(), "Ogrodowa");
        assertEquals(address2.getStreet(), "Miodowa");
    }

    @org.junit.Test
    public void getCity() {
        assertEquals(address.getCity(), "Gdansk");
        assertEquals(address1.getCity(), "Krakow");
        address2.setCity("Dąbrowa Górnicza");
        assertEquals(address2.getCity(), "Dąbrowa Górnicza");
    }

    @org.junit.Test
    public void getPostalCode() {
        address.setPostalCode("34-125");
        assertEquals(address.getPostalCode(), "34-125");
        assertEquals(address1.getPostalCode(), "34-526");
        assertEquals(address2.getPostalCode(), "34-969");
    }


}