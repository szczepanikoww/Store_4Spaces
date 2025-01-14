package com.storefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import store.Product;
import store.Store;

import java.io.File;

public class EditSingleProduct {
    public EditProductsController editproducts;
    public Store store;
    public Product product;

    public void setStore(Store store) {
        this.store = store;
    }

    @FXML
    public TextArea newProductDescriptionTextArea;
    public Button ChangeImageButton;
    public ImageView newProductImageView;
    public TextField newProductPrice;
    public TextField newProductQuantity;
    public Button SaveNewChangesButton;
    public Button CancelChangingButton;
    public Button clearFormButton;
    public File selectedImageFile;

    @FXML
    public void setMainController(EditProductsController editproducts) {
        this.editproducts = editproducts;
    }

    public void setProduct(Product product){
        this.product = product;
        newProductDescriptionTextArea.setText(product.getProductDescription());
        newProductImageView.setImage(product.getImage());
        newProductPrice.setText(String.valueOf(product.getPrice()));
        newProductQuantity.setText(String.valueOf(product.getQuantity()));
    }

    public void clearForm(){
        newProductDescriptionTextArea.clear();
        newProductImageView.setImage(null);
        newProductPrice.clear();
        newProductQuantity.clear();
    }

    public void saveChanges(ActionEvent actionEvent) {
        try {
            product.setProductPrice(Double.parseDouble(newProductPrice.getText()));
            product.setProductQuantity(Integer.parseInt(newProductQuantity.getText()));
            if(newProductDescriptionTextArea.getText().isEmpty()){
                product.setProductDescription(newProductDescriptionTextArea.getText());
            }else {
                product.setProductDescription(product.getProductDescription());
            }
            if (selectedImageFile != null) {
                product.setImage(new Image(selectedImageFile.toURI().toString()));
            }
            editproducts.updateProductPane(product);
            showSucces("Pomyślnie zapisano zmiany");
        }catch (Exception e){
            showError("Nie udało się zapisać zmian");
            e.printStackTrace();
        }

        Stage stage = (Stage) SaveNewChangesButton.getScene().getWindow();
        stage.close();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

    private void showSucces(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukces");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void cancelChanges(ActionEvent actionEvent) {
        clearForm();
        Stage stage = (Stage) CancelChangingButton.getScene().getWindow();
        stage.close();
    }

    public void changePhoto(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        selectedImageFile = fileChooser.showOpenDialog(null);
        if (selectedImageFile != null) {
            newProductImageView.setImage(new Image(selectedImageFile.toURI().toString()));
        }
    }
}
