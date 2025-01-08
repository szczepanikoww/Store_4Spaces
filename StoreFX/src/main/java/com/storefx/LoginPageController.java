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
    private String userType;



    public void setUserType(String userType) {
        this.userType = userType;
    }


    public void onLoginButton(ActionEvent actionEvent) {
        String login = LoginTextField.getText();
        String password = PasswordField.getText();

        if(login.isEmpty() || password.isEmpty())
        {
            showError("Wprowadź login i hasło - te pola nie mogą być puste");
            return;
        }


        //podczas wybierania logowania decydujemy czy logujemy admina czy usera
        //zrobilem to w kontrolerze mainPageController
        //w zaleznosci od wyboru, przekazujemy odpowiedni typ uzytkownika do tej klasy (userType i metoda setUserType)

        if ("Admin".equals(userType)){
            for(Admin a: admins)
            {
                if(a.getLogin().equals(login) && a.getPassword().equals(password))
                {
                    //logowanie admina
                    System.out.println("Zalogowano admina");
                    break;
                }
            }
        }
        else if ("Klient".equals(userType)){
            for(Customer c: customers)
            {
                if(c.getLogin().equals(login) && c.getPassword().equals(password))
                {
                    //logowanie klienta
                    System.out.printf("Zalogowano klienta");
                    break;
                }
            }
        }

        showError("Niepoprawne dane logowania");


    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd!!!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void onCancelLoginButton(ActionEvent actionEvent) {
        Stage stage = (Stage) CancelLoginButton.getScene().getWindow();
        stage.close();
    }


    //public void initialize() {
      //  Image Logo = new Image(getClass().getResource("/images/logo.jpg").toExternalForm());
       // LoginLogoImageView.setImage(Logo);
        //LoginLogoImageView.setFitWidth(109);
       // LoginLogoImageView.setFitHeight(83);
       // LoginLogoImageView.setTranslateX(0);
       // LoginLogoImageView.setTranslateY(0);
    //}
}