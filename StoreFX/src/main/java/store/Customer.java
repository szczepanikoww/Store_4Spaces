package store;

public class Customer extends User{
    private String CustomerName;
    private String CustomerSurname;
    private String CustomerEmail;
    private String CustomerPhoneNumber;
    private Address CustomerAddress;

    //Constructor
    public Customer(int userID, String userName, String userPassword, String customerName, String customerSurname, String customerEmail, String customerPhoneNumber, Address customerAddress) {
        super(userID, userName, userPassword);
        CustomerName = customerName;
        CustomerSurname = customerSurname;
        CustomerEmail = customerEmail;
        CustomerPhoneNumber = customerPhoneNumber;
        CustomerAddress = customerAddress;
    }

    //setters
    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public void setCustomerSurname(String customerSurname) {
        CustomerSurname = customerSurname;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public void setCustomerPhoneNumber(String phoneNumber) {
    }

    public void setCustomerAddress(Address customerAddress) {
        CustomerAddress = customerAddress;
    }

    //getters
    public String getCustomerName() {
        return CustomerName;
    }

    public String getCustomerSurname() {
        return CustomerSurname;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public Address getCustomerAddress() {
        return CustomerAddress;
    }

    public String getLogin() {
        return this.getUserName();
    }

    public String getPassword() {
        return this.getUserPassword();
    }

    public String getDetails() {
        return "Customer{" +
                "CustomerName='" + CustomerName + '\'' +
                ", CustomerSurname='" + CustomerSurname + '\'' +
                ", CustomerEmail='" + CustomerEmail + '\'' +
                ", CustomerPhoneNumber='" + CustomerPhoneNumber + '\'' +
                ", CustomerAddress=" + CustomerAddress +
                '}';
    }
}
