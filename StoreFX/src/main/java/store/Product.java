package store;

abstract class Product {
    private int ProductID;
    private String ProductName; //można tutaj dać ProductModel zamiast ProductName
    private double ProductPrice;
    private int ProductQuantity;
    private String ProductBrand;

    //Constructor
    public Product(int productID, String productName, double productPrice, int productQuantity, String productBrand) {
        this.ProductID = productID;
        this.ProductName = productName;
        this.ProductPrice = productPrice;
        this.ProductQuantity = productQuantity;
        this.ProductBrand = productBrand;
    }

    //setters
    public void setProductName(String productName) {
        this.ProductName = productName;
    }

    public void serProductPrice(double productPrice) {
        this.ProductPrice = productPrice;
    }

    public void setProductQuantity(int productQuantity) {
        this.ProductQuantity = productQuantity;
    }
    public void setProductBrand (String productBrand) {
        this.ProductBrand = productBrand;
    }

    //getters
    public int getProductID() {
        return ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public double getPrice() {
        return ProductPrice;
    }

    public int getQuantity() {
        return ProductQuantity;
    }

    public String getBrand() {
        return ProductBrand;
    }

    public String getDetails() {
        return "Product ID: " + ProductID + "\nProduct Name: " + ProductName + "\nProduct Price: " + ProductPrice + "\nProduct Quantity: " + ProductQuantity + "\nProduct Brand: " + ProductBrand;
    }

    // other methods
    public void updateProductQuantity(int quantity) {
        ProductQuantity += quantity;
    }

}
