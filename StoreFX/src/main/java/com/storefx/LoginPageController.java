package com.storefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import store.Admin;
import store.Customer;
import store.Store;

import java.util.List;

public class LoginPageController {
    @FXML
    public ImageView LoginLogoImageView;

    @FXML
    public TextField LoginTextField;
    public PasswordField PasswordField;
    public Button LoginButton, CancelLoginButton;

    private List<Admin> admins = Store.getAdmins();
    private List<Customer> customers = Store.getCustomers();

    public void onCancelLoginButton(ActionEvent actionEvent) {
        String login = LoginTextField.getText();
        String password = PasswordField.getText();

        if(login.isEmpty() || password.isEmpty())
        {
            showError("Wprowadź login i hasło - te pola nie mogą być puste");
            return;
        }

        //trzeba jakoś wprowadzić rozroznienie między adminem a customerem, bo logowanie będzie polegało na tym, że
        //wpisujemy login i hasło, następnie przeszukujemy albo tablicę adminów, albo tablicę userów
        // i sprawdzamy czy istnieje taki login i hasło, jeśli tak to logujemy, jeśli nie to nie logujemy

        //na razie nie wiem jak to zrobić

        for(Admin a: admins)
        {
            if(a.getLogin().equals(login) && a.getPassword().equals(password))
            {
                //logowanie admina
                break;
            }
        }

        for(Customer c: customers)
        {
            if(c.getLogin().equals(login) && c.getPassword().equals(password))
            {
                //logowanie usera
                break;
            }
        }

        //dodatkowo trzeba stworzyc zmienna, która bedzie mowila jaki uzytkownik sie zalogowal
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd!!!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void onLoginButton(ActionEvent actionEvent) {
        Stage stage = (Stage) CancelLoginButton.getScene().getWindow();
        stage.close();
    }

    public void initialize() {
        Image Logo = new Image(getClass().getResource("/images/logo.jpg").toExternalForm());
        LoginLogoImageView.setImage(Logo);
        LoginLogoImageView.setFitWidth(109);
        LoginLogoImageView.setFitHeight(83);
        LoginLogoImageView.setTranslateX(0);
        LoginLogoImageView.setTranslateY(0);
    }
}