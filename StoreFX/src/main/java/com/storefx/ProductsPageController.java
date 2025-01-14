package com.storefx;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import store.Product;


import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

import java.util.List;

import static javafx.scene.layout.Priority.SOMETIMES;

public class ProductsPageController {
    public VBox productsVbox;
    public GridPane productsGrid;

    public void setProducts(List<Product> products){
        productsGrid.getChildren().clear();
        int column = 0;
        int row = 0;
        for(Product product : products){
            VBox productVbox = createProductsVbox(product);
            productsGrid.add(productVbox, column, row);
            column++;
            if(column == 4){
                column = 0;
                row++;
            }
        }

    }



    public VBox createProductsVbox(Product product){
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

        vbox.getChildren().addAll(imageView, nameLabel, detailsLabel, priceLabel, addToCartButton);

        return vbox;

    }





}