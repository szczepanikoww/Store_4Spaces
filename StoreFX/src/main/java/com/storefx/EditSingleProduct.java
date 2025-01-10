package com.storefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import store.Store;

public class EditSingleProduct {
    public EditProductsController editproducts;
    public Store store;

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

    @FXML
    public void setMainController(EditProductsController editproducts) {
        this.editproducts = editproducts;
    }

    public void clearForm(){
        newProductDescriptionTextArea.clear();
        newProductImageView.setImage(null);
        newProductPrice.clear();
        newProductQuantity.clear();
    }

    public void saveChanges(ActionEvent actionEvent) {
        //do dokonczenia
    }

    public void cancelChanges(ActionEvent actionEvent) {
        clearForm();
        // Zamknięcie okna
        Stage stage = (Stage) CancelChangingButton.getScene().getWindow();
        stage.close();
    }
}
