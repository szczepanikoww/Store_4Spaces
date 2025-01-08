package store;

public class Address {
    private String street;
    private String city;
    private String postalCode;
    private String country;
    private int numberOnStreet;

    //Constructor
    public Address(String street, String city, String postalCode, String country, int numberOnStreet) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.numberOnStreet = numberOnStreet;
    }

    //setters
    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNumberOnStreet(int numberOnStreet){
        this.numberOnStreet = numberOnStreet;
    }

    //getters
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public int getNumberOnStreet() {
        return numberOnStreet;
    }
    
}
