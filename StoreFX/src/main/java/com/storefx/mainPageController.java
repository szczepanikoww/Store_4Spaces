package com.storefx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import store.*;
import com.storefx.CartPageController;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class mainPageController {

    public Store store;
    public Customer aktCustomer;

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
    private CartPageController cartPageController;


    //dodawanie produktow do inventory na start sklepu
    public void initialize() {
        store = new Store();
        System.out.println(store);

        //dodawanie produktow do inventory na start sklepu
        Phone phone1 = new Phone("Samsung Galaxy S21", 2999.99, 50, "Samsung", "Android", "Exynos 1200", 8, 128, "50", "6.6", "4000");
        Phone phone2 = new Phone("iPhone 12", 3999.99, 50, "Apple", "iOS", "A14 Bionic", 8, 128, "50", "6.1", "2815");
        Phone phone3 = new Phone("Xiaomi Mi 11", 2499.99, 50, "Xiaomi", "Android", "Snapdragon 888", 8, 128, "50", "6.8", "4600");
        Tablet tablet1 = new Tablet("Samsung Galaxy Tab S9", 2999.99, 50, "Samsung", "Android", "Snapdragon 865+", 8, 128, "5000mAh", 11);
        Tablet tablet2 = new Tablet("iPad Pro 6th", 3999.99, 50, "Apple", "iOS", "A12Z Bionic", 8, 128, "9720mAh", 12.9);
        Tablet tablet3 = new Tablet("Xiaomi Mi Pad 5", 2499.99, 50, "Xiaomi", "Android", "Snapdragon 860", 8, 128, "8720mAh", 11);
        Laptop laptop1 = new Laptop("Dell XPS 13", 4999.99, 50, "Dell", "Windows", "Intel Core i7-1165G7", 16, 512, "13.4", "1920x1200", "52");
        Laptop laptop2 = new Laptop("MacBook Pro 13", 6999.99, 50, "Apple", "macOS", "Apple M1", 16, 512, "13.3", "2560x1600", "58");
        Laptop laptop3 = new Laptop("Asus ROG Zephyrus G14", 3999.99, 50, "Asus", "Windows", "AMD Ryzen 9 5900HS", 16, 512, "14", "2560x1440", "76");


        phone1.setImage("StoreFX/src/main/resources/images/s21.jpg");
        phone2.setImage("StoreFX/src/main/resources/images/ip12.jpg");
        phone3.setImage("StoreFX/src/main/resources/images/mi11.jpg");
        tablet1.setImage(new Image(getClass().getResource("/images/tabs9.jpg").toExternalForm()));
        tablet2.setImage(new Image(getClass().getResource("/images/ipadPro.jpeg").toExternalForm()));
        tablet3.setImage(new Image(getClass().getResource("/images/miPad.png").toExternalForm()));
        laptop1.setImage(new Image(getClass().getResource("/images/dellXps.jpg").toExternalForm()));
        laptop2.setImage(new Image(getClass().getResource("/images/macBook13.jpg").toExternalForm()));
        laptop3.setImage(new Image(getClass().getResource("/images/asus.jpg").toExternalForm()));


        store.getInventory().addProduct(phone1);
        store.getInventory().addProduct(phone2);
        store.getInventory().addProduct(phone3);
        store.getInventory().addProduct(tablet1);
        store.getInventory().addProduct(tablet2);
        store.getInventory().addProduct(tablet3);
        store.getInventory().addProduct(laptop1);
        store.getInventory().addProduct(laptop2);
        store.getInventory().addProduct(laptop3);


        Customer customer1 = new Customer(1, "Klient", "Klient", "Jan", "Kowalski", "jankowaliski@wp.pl", "123456789", null);
        Customer customer2 = new Customer(2, "Klient2", "Klient2", "Adam", "Nowak", "adamnowak@wp.pl", "987654321", null);

        Address addres1 = new Address("Polska", "Kazimierza", "Kraków", "30-000", 1);
        Customer customer3 = new Customer(3, "Klient3", "Klient3", "Jan", "Bączek", "janbaczek@email.com", "123456789", addres1);
        store.getCustomers().add(customer1);
        store.getCustomers().add(customer2);
        store.getCustomers().add(customer3);


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
    public void loadPageWithScroll(String fxmlFile, String category){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            ScrollPane newPane = loader.load();
            ProductsPageController controller = loader.getController();
            controller.setProducts(store.getInventory().getProducts(), category);
            controller.setStore(this.store);
            centerPane.getChildren().clear();
            centerPane.getChildren().add(newPane);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void onSmartphonesMenuButton() {
        loadPageWithScroll("productsPage.fxml", "Phone");
    }
    public void onTabletsMenuButton() {
        loadPageWithScroll("productsPage.fxml", "Tablet");
    }
    public void onLaptopsMenuButton() {
        loadPageWithScroll("productsPage.fxml", "Laptop");
    }
    public void onAllMenuButton() {
       loadPageWithScroll("productsPage.fxml", "All");
    }

    // zmieniłem tu zeby przekazywlo store do cartPageController
    public void onCartMenuButton() {
        System.out.println("Cart dziala");
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cartPage.fxml"));
            Pane newPane = loader.load();
            CartPageController controller = loader.getController();
            controller.setMainController(this);
            controller.setStore(this.store);
            controller.setAktCustomer(aktCustomer);
            centerPane.getChildren().clear();
            centerPane.getChildren().add(newPane);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void onHomeMenuButton() {
        loadPage("homePage.fxml");
    }

    //zmienianie napisu w zaleznosci od tego czy jestesmy zalogowani czy nie
    public void setLoginLabel(String username, Customer customer){
        this.aktCustomer = customer;
        loginLabel.setText("Witaj, " + username);
        loginMenu.getItems().clear();
        MenuItem userDataItem = new MenuItem("Dane użytkownika");
        userDataItem.setOnAction(actionEvent -> loadUserDataPage(customer));
        MenuItem orderHistoryItem = new MenuItem("Historia zamówień");
        orderHistoryItem.setOnAction(actionEvent -> {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("previousOrders.fxml"));
            Pane newPane = loader.load();
            PreviousOrdersController controller = loader.getController();
            controller.setMainController(this);
            controller.setStore(this.store, aktCustomer);
            centerPane.getChildren().clear();
            centerPane.getChildren().add(newPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
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
            controller.setAktCustomer(aktCustomer);
            controller.setMainController(this);
            centerPane.getChildren().clear();
            centerPane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}