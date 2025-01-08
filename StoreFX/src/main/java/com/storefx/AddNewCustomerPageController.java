package com.storefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import store.Admin;
import store.Customer;
import store.Store;

import java.util.List;

public class AddNewCustomerPageController {
    @FXML
    public PasswordField UserPassword;
    public TextField userName, Name, Surname, Email, PhoneNumber, Country, City, Street, NumberOnStreet, PostalCode;
    public CheckBox RegulaminCheckBox, ZgodaCheckBox;
    public Button CreateAccountButton, CancelCreatingAccountButton;

    public void showError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void onCreateAccountButton(ActionEvent actionEvent) {
        try {
            if (RegulaminCheckBox.isSelected() && ZgodaCheckBox.isSelected()){
                if (userName.getText().isEmpty() || UserPassword.getText().isEmpty() || Name.getText().isEmpty() || Surname.getText().isEmpty() || Email.getText().isEmpty() || PhoneNumber.getText().isEmpty() || Country.getText().isEmpty() || City.getText().isEmpty() || Street.getText().isEmpty() || NumberOnStreet.getText().isEmpty() || PostalCode.getText().isEmpty()){
                    showError("Wszystkie pola muszą być wypełnione");
                }else {
                    List<Customer> customers = Store.getInstance().getCustomers();
                    for (Customer customer : customers){
                        if (customer.getUserName().equals(userName.getText())){
                            showError("Użytkownik o podanej nazwie już istnieje");
                            return;
                        }
                    }
                    //to jeszcze muszę dokończyć
                }
            }
        }catch (Exception e){
            showError("Musisz zaznaczyć wymagane zgody");
        }
    }

    public void onCancelCreatingButton(ActionEvent actionEvent) {
    }
}