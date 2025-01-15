package com.storefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import store.Address;
import store.Customer;
import store.Store;

import java.io.IOException;

public class UserDataPageController {
    @FXML
    public TextField currentUserName, currentName, currentSurname, currentEmail,
                    currentPhoneNumber, currentCountry, currentCity, currentStreet,
                    currentNumberOnStreet, currentPostalCode, newUserName, newName,
                    newSurname, newEmail, newPhoneNumber, newCountry, newCity, newStreet,
                    newNumberOnStreet, newPostalCode;

    @FXML
    public Button SaveChangesButton, ClearFormButton;

    private Customer aktCustomer;
    private mainPageController mainController;

    public void initialize(){
    }
    public void setAktCustomer(Customer customer){
        this.aktCustomer = customer;
    }

    public void setMainController(mainPageController mainController) {
        this.mainController = mainController;
    }

    public void setCustomerData(Customer customer){
        currentUserName.setText(customer.getUserName());
        currentName.setText(customer.getCustomerName());
        currentSurname.setText(customer.getCustomerSurname());
        currentEmail.setText(customer.getCustomerEmail());
        currentPhoneNumber.setText(customer.getCustomerPhoneNumber());
        currentCountry.setText(customer.getCustomerAddress().getCountry());
        currentCity.setText(customer.getCustomerAddress().getCity());
        currentStreet.setText(customer.getCustomerAddress().getStreet());
        currentNumberOnStreet.setText(String.valueOf(customer.getCustomerAddress().getNumberOnStreet()));
        currentPostalCode.setText(customer.getCustomerAddress().getPostalCode());
    }


    public void onSaveChangesButton(ActionEvent actionEvent) {
        String userName = (newUserName.getText() == null || newUserName.getText().isEmpty()) ? currentUserName.getText() : newUserName.getText();
        String name = (newName.getText() == null || newName.getText().isEmpty()) ? currentName.getText() : newName.getText();
        String surname = (newSurname.getText() == null || newSurname.getText().isEmpty()) ? currentSurname.getText() : newSurname.getText();
        String email = (newEmail.getText() == null || newEmail.getText().isEmpty()) ? currentEmail.getText() : newEmail.getText();
        String phoneNumber = (newPhoneNumber.getText() == null || newPhoneNumber.getText().isEmpty()) ? currentPhoneNumber.getText() : newPhoneNumber.getText();
        String country = (newCountry.getText() == null || newCountry.getText().isEmpty()) ? currentCountry.getText() : newCountry.getText();
        String street = (newStreet.getText() == null || newStreet.getText().isEmpty()) ? currentStreet.getText() : newStreet.getText();
        String city = (newCity.getText() == null || newCity.getText().isEmpty()) ? currentCity.getText() : newCity.getText();
        String postalCode = (newPostalCode.getText() == null || newPostalCode.getText().isEmpty()) ? currentPostalCode.getText() : newPostalCode.getText();
        int numberOnStreet = (newNumberOnStreet.getText() == null || newNumberOnStreet.getText().isEmpty()) ? Integer.parseInt(currentNumberOnStreet.getText()) : Integer.parseInt(newNumberOnStreet.getText());


        try{
            aktCustomer.setUserName(userName);
            aktCustomer.setCustomerName(name);
            aktCustomer.setCustomerSurname(surname);
            aktCustomer.setCustomerEmail(email);
            aktCustomer.setCustomerPhoneNumber(phoneNumber);

            aktCustomer.getCustomerAddress().setCity(city);
            aktCustomer.getCustomerAddress().setCountry(country);
            aktCustomer.getCustomerAddress().setStreet(street);
            aktCustomer.getCustomerAddress().setPostalCode(postalCode);
            aktCustomer.getCustomerAddress().setNumberOnStreet(numberOnStreet);
            showSuccess("Dane zostały zaktualizowane");

            mainController.loadUserDataPage(aktCustomer);
            } catch (NumberFormatException e) {
            showError("Error: " + e.getMessage());
             } catch (Exception e) {
            showError("Error: " + e.getMessage());
    }


    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd!!!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukces");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public void onClearFormAction(ActionEvent actionEvent) {
        newUserName.clear();
        newName.clear();
        newSurname.clear();
        newEmail.clear();
        newPhoneNumber.clear();
        newCountry.clear();
        newCity.clear();
        newStreet.clear();
        newNumberOnStreet.clear();
        newPostalCode.clear();
    }





}