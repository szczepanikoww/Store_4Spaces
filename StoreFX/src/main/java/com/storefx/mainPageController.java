package com.storefx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import store.Customer;
import store.Phone;
import store.Store;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class mainPageController {

    public Store store;

    public Label HomeMenuButton;
    public Label SmartphonesMenuButton;
    public Label TabletsMenuButton;
    public Label LaptopsMenuButton;
    public Label AllMenuButton;
    public MenuItem ClientSelectButton;
    public MenuItem AdminSelectButton;
    public AnchorPane centerPane;
    public Label CartMenuButton;
    public Label loginLabel;
    public Menu loginMenu;

    private LoginPageController loginPageController;


    //dodawanie produktow do inventory na start sklepu
    public void initialize() {
        store = new Store();
        System.out.println(store);
        Phone phone1 = new Phone("Samsung Galaxy S21", 2999.99, 50, "Samsung", "Android", "Exynos 1200", 8, 128, "50", "6.6", "4000");
        Phone phone2 = new Phone("iPhone 12", 3999.99, 50, "Apple", "iOS", "A14 Bionic", 8, 128, "50", "6.1", "2815");
        Phone phone3 = new Phone("Xiaomi Mi 11", 2499.99, 50, "Xiaomi", "Android", "Snapdragon 888", 8, 128, "50", "6.8", "4600");

        phone1.setImage("StoreFX/src/main/resources/images/s21.jpg");
        phone2.setImage("StoreFX/src/main/resources/images/ip12.jpg");
        phone3.setImage("StoreFX/src/main/resources/images/mi11.jpg");

        store.getInventory().addProduct(phone1);
        store.getInventory().addProduct(phone2);
        store.getInventory().addProduct(phone3);
    }
    public void setLoginPageController(LoginPageController loginPageController){
        this.loginPageController = loginPageController;
    }


    //otwieranie okna logowania (niezależnie od tego czy jesteśmy adminem czy klientem)
    public void openLoginWidow(String userType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
            Parent root = loader.load();
            LoginPageController controller = loader.getController();
            controller.setUserType(userType);
            controller.setStore(store); // ustawia sklep w kontrolerze logowania
            controller.setMainController(this);
            this.loginPageController = controller;

            Stage stage = new Stage();
            stage.setTitle("Logowanie - 4Spaces - " + userType);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    //wywolywanie okna logowania zaleznie od tego czy jestesmy adminem czy klientem

    public void onClientSelectButton(javafx.event.ActionEvent actionEvent) {
        openLoginWidow("Klient");
    }

    public void onAdminSelectButton(javafx.event.ActionEvent actionEvent) {
        openLoginWidow("Admin");
    }

    //ladowanie strony wewnatrz centrePane
    public void loadPage(String fxmlFile){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane newPane = loader.load();
            centerPane.getChildren().clear();
            centerPane.getChildren().add(newPane);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //ladowanie strony ze scrollem
    public void loadPageWithScroll(String fxmlFile){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            ScrollPane newPane = loader.load();
            centerPane.getChildren().clear();
            centerPane.getChildren().add(newPane);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void onSmartphonesMenuButton() {
        loadPageWithScroll("productsPage.fxml");
    }
    public void onTabletsMenuButton() {
        loadPageWithScroll("productsPage.fxml");
    }
    public void onLaptopsMenuButton() {
        loadPageWithScroll("productsPage.fxml");
    }
    public void onAllMenuButton() {
        loadPageWithScroll("productsPage.fxml");
    }
    public void onCartMenuButton() {
        System.out.println("Cart dziala");
        loadPage("cartPage.fxml");
    }
    public void onHomeMenuButton() {
        loadPage("homePage.fxml");
    }


    //zmienianie napisu w zaleznosci od tego czy jestesmy zalogowani czy nie
    public void setLoginLabel(String username, Customer customer){
        loginLabel.setText("Witaj, " + username);
        loginMenu.getItems().clear();
        MenuItem userDataItem = new MenuItem("Dane użytkownika");
        userDataItem.setOnAction(actionEvent -> loadUserDataPage(customer));
        MenuItem orderHistoryItem = new MenuItem("Historia zamówień");
        orderHistoryItem.setOnAction(actionEvent -> loadPage("previousOrdersPage.fxml"));
        MenuItem logoutItem = new MenuItem("Wyloguj");
        logoutItem.setOnAction(actionEvent -> onLogoutButton());
        loginMenu.getItems().addAll(userDataItem, orderHistoryItem, logoutItem);

    }
    public void setLoginLabelAdmin(String username){
        loginLabel.setText("Witaj, " + username);
        loginMenu.getItems().clear();
        MenuItem adminPageItem = new MenuItem("Panel admina");
        adminPageItem.setOnAction(actionEvent -> loginPageController.openEditsWindow());
        loginMenu.getItems().add(adminPageItem);
        MenuItem logoutItem = new MenuItem("Wyloguj");
        logoutItem.setOnAction(actionEvent -> onLogoutButton());
        loginMenu.getItems().addAll(logoutItem);
    }

    public void onLogoutButton(){
        loginLabel.setText("LOGOWANIE");
        loginMenu.getItems().clear();
        loginMenu.getItems().addAll(ClientSelectButton, AdminSelectButton);
        loadPage("homePage.fxml");

    }

    public void loadUserDataPage(Customer customer) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userDataPage.fxml"));
            Parent root = loader.load();
            UserDataPageController controller = loader.getController();
            controller.setCustomerData(customer);
            centerPane.getChildren().clear();
            centerPane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}