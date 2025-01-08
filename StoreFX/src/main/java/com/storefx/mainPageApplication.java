package com.storefx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainPageApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainPageApplication.class.getResource("mainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 760,440 );
        stage.setTitle("Store - 4Spaces");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}