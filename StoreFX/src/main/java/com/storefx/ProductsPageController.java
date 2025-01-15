package com.storefx;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import store.Product;
import store.Store;

import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

import java.util.List;


public class ProductsPageController {
    public VBox productsVbox;
    public GridPane productsGrid;
    public Store store;
    public Button addToCartButton;
    public mainPageController mainPageController;

    public void setStore(Store store){
        this.store = store;
    }

    public void setMainController(mainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }
    public void setProducts(List<Product> products, String category){
        productsGrid.getChildren().clear();
        int column = 0;
        int row = 0;
        for (Product product : products) {
            if ("Phone".equals(category) && "Phone".equals(product.getProductCategory())) {
                VBox productVbox = createProductsVbox(product);
                productsGrid.add(productVbox, column, row);
                column++;
                if (column == 4) {
                    column = 0;
                    row++;
                }
            }
            if ("Laptop".equals(category) && "Laptop".equals(product.getProductCategory())) {
                VBox productVbox = createProductsVbox(product);
                productsGrid.add(productVbox, column, row);
                column++;
                if (column == 4) {
                    column = 0;
                    row++;
                }

            }
            if ("Tablet".equals(category) && "Tablet".equals(product.getProductCategory())) {
                VBox productVbox = createProductsVbox(product);
                productsGrid.add(productVbox, column, row);
                column++;
                if (column == 4) {
                    column = 0;
                    row++;
                }
            }

            if ("All".equals(category)) {
                VBox productVbox = createProductsVbox(product);
                productsGrid.add(productVbox, column, row);
                column++;
                if (column == 4) {
                    column = 0;
                    row++;
                }

            }

        }


    }

    public VBox createProductsVbox(Product product) {
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #E0E0E0; -fx-border-radius: 10; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);");
        vbox.setAlignment(Pos.TOP_CENTER);

        ImageView imageView = new ImageView(product.getImage());
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);

        Label nameLabel = new Label(product.getProductName());
        nameLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");

        Label detailsLabel = new Label(product.getDetails());
        detailsLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666666;");

        Label priceLabel = new Label(product.getPrice() + " PLN");
        priceLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #FF5722; -fx-font-weight: bold;");

        Button addToCartButton = new Button("Add to cart");
        addToCartButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 12px; -fx-cursor: hand;");
        addToCartButton.setOnAction(e -> onAddCartButton(product));
        addToCartButton.setId("addToCartButton");
        vbox.getChildren().addAll(imageView, nameLabel, detailsLabel, priceLabel, addToCartButton);

        return vbox;

    }

    public void removeVbox(VBox vbox){
        productsGrid.getChildren().remove(vbox);
    }

    public void onAddCartButton(Product product){
        store.getCart().addProduct(product,1);
        product.setProductQuantity(product.getQuantity()-1);
        if(product.getQuantity() > 0) {
            refreshQuantity(product);
        }else if(product.getQuantity() == 0){
            store.getInventory().removeProduct(product);
            removeVbox(productsVbox);
            if(product.getProductCategory().equals("Phone")) {
                mainPageController.loadPageWithScroll("productsPage.fxml", "Phone");
            }else if(product.getProductCategory().equals("Laptop")){
                mainPageController.loadPageWithScroll("productsPage.fxml", "Laptop");
            }else if(product.getProductCategory().equals("Tablet")){
                mainPageController.loadPageWithScroll("productsPage.fxml", "Tablet");
            }
        }
    }

    public void refreshQuantity(Product product){
        for(int i = 0; i < productsGrid.getChildren().size(); i++){
            VBox vbox = (VBox) productsGrid.getChildren().get(i);
            Label nameLabel = (Label) vbox.getChildren().get(1);
            if(nameLabel.getText().equals(product.getProductName())){
                Label detailsLabel = (Label) vbox.getChildren().get(2);
                detailsLabel.setText(product.getDetails());
            }
        }
    }

}