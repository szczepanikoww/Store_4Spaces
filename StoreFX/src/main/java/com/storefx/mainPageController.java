package com.storefx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class mainPageController {


    public Label HomeMenuButton;
    public Label SmartphonesMenuButton;
    public Label TabletsMenuButton;
    public Label LaptopsMenuButton;
    public Label AllMenuButton;
    public RadioMenuItem ClientSelectButton;
    public RadioMenuItem AdminSelectButton;




    //otwieranie okna logowania (niezależnie od tego czy jesteśmy adminem czy userem)
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

    //wywolywanie okna logowania zaleznie od tego czy jestesmy adminem czy userem

    public void onClientSelectButton(javafx.event.ActionEvent actionEvent) {
        openLoginWidow("Klient");
    }

    public void onAdminSelectButton(javafx.event.ActionEvent actionEvent) {
        openLoginWidow("Admin");
    }
    public void onHomeMenuButton() {
        System.out.println("Home dziala");
    }
}