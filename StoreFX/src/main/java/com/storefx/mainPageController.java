package com.storefx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class mainPageController {


    public Label HomeMenuButton;
    public Label SmartphonesMenuButton;
    public Label TabletsMenuButton;
    public Label LaptopsMenuButton;
    public Label AllMenuButton;
    public MenuItem ClientSelectButton;
    public MenuItem AdminSelectButton;
    public AnchorPane centerPane;
    public Label CartMenuButton;


    //otwieranie okna logowania (niezależnie od tego czy jesteśmy adminem czy klientem)
    public void openLoginWidow(String userType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
            Parent root = loader.load();
            LoginPageController controller = loader.getController();
            controller.setUserType(userType);

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

}