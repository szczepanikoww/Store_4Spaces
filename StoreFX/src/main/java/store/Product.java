package store;

import javafx.scene.image.Image;

public class Product {
    private String ProductName; //można tutaj dać ProductModel zamiast ProductName
    private double ProductPrice;
    private int ProductQuantity;
    private String ProductBrand;
    private Image ProductImage;
    private String ProductDescription;
    private String ProductCategory;

    //Constructor
    public Product() {
    }
    public Product(String productName, double productPrice, int productQuantity, String productBrand) {
        this.ProductName = productName;
        this.ProductPrice = productPrice;
        this.ProductQuantity = productQuantity;
        this.ProductBrand = productBrand;
    }

    //setters
    public void setProductName(String productName) {
        this.ProductName = productName;
    }

    public void setProductPrice(double productPrice) {
        this.ProductPrice = productPrice;
    }

    public void setProductQuantity(int productQuantity) {
        this.ProductQuantity = productQuantity;
    }
    public void setProductBrand (String productBrand) {
        this.ProductBrand = productBrand;
    }

    public void setImage(Image productImage) {
        this.ProductImage = productImage;
    }

    public void setProductDescription(String productDescription) {
        this.ProductDescription = productDescription;
    }

    public void setProductCategory(String productCategory) {
        this.ProductCategory = productCategory;
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
        return "\nProduct Quantity: " + ProductQuantity + "\nProduct Brand: " + ProductBrand;
    }

    public Image getImage() {
        return ProductImage;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public String getProductCategory() {
        return ProductCategory;
    }
}
