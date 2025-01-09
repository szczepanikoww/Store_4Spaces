package com.storefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import store.Address;
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

    public void showSuccess(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void onCreateAccountButton(ActionEvent actionEvent) {
        try {
            if (RegulaminCheckBox.isSelected() && ZgodaCheckBox.isSelected()){
                if (userName.getText().isEmpty() || UserPassword.getText().isEmpty() || Name.getText().isEmpty() || Surname.getText().isEmpty() || Email.getText().isEmpty() || PhoneNumber.getText().isEmpty() || Country.getText().isEmpty() || City.getText().isEmpty() || Street.getText().isEmpty() || NumberOnStreet.getText().isEmpty() || PostalCode.getText().isEmpty()){
                    showError("Wszystkie pola muszą być wypełnione");
                }else {
                    List<Customer> customers = Store.getCustomers();
                    for (Customer customer : customers){
                        if (customer.getUserName().equals(userName.getText())){
                            showError("Użytkownik o podanej nazwie już istnieje");
                            return;
                        }
                    }

                    Address address = new Address(Country.getText(), Street.getText(), City.getText(), PostalCode.getText(), Integer.parseInt(NumberOnStreet.getText()));
                    Customer newCustomer = new Customer(customers.size()+1 ,userName.getText(), UserPassword.getText(), Name.getText(), Surname.getText(), Email.getText(), PhoneNumber.getText(), address);
                    customers.add(newCustomer);
                    showSuccess("Konto zostało utworzone");
                    Stage stage = (Stage) CreateAccountButton.getScene().getWindow();
                    stage.close();
                }
            }
        }catch (Exception e){
            showError("Musisz zaznaczyć wymagane zgody");
            e.printStackTrace();
        }
    }

    public void onCancelCreatingButton(ActionEvent actionEvent) {
        Stage stage = (Stage) CancelCreatingAccountButton.getScene().getWindow();
        stage.close();
    }


}