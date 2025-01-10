package com.storefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import store.Admin;
import store.Customer;
import store.Store;

import java.io.IOException;
import java.util.List;

public class LoginPageController {

    public Store store;
    private String userType;
    private mainPageController mainController;

    @FXML
    public ImageView LoginLogoImageView;

    @FXML
    public TextField LoginTextField;
    public PasswordField PasswordField;
    public Button LoginButton, CancelLoginButton;
    public Button signInButton;
    public Label noAccountLabel;

    private List<Admin> admins = Store.getAdmins();
    private List<Customer> customers = Store.getCustomers();

    //ta metoda przekazuje store utworzony w initialize() w mainPageController
    public void setStore(Store store) {
        this.store = store;
        System.out.println("Store set in LoginPageController: " + store);
    }

    public void setUserType(String userType) {
        this.userType = userType;
        if("Admin".equals(userType)){
            signInButton.setVisible(false);
            noAccountLabel.setVisible(false);
        }
    }

    public void setMainController(mainPageController mainController) {
        this.mainController = mainController;
    }

    //to otwiera okno edycji dla admina
    public void openEditsWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editProducts.fxml"));
            Parent root = loader.load();
            EditProductsController controller = loader.getController();
            controller.setStore(store);
            controller.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Zarządzanie magazynem");
            stage.setScene(new Scene(root));
            stage.show();

            Stage loginStage = (Stage) LoginButton.getScene().getWindow();
            loginStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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

        boolean loginSuccessful = false;
        if ("Admin".equals(userType)){
            for(Admin a: admins)
            {
                if(a.getLogin().equals(login) && a.getPassword().equals(password))
                {
                    //logowanie admina
                    System.out.println("Zalogowano admina");
                    mainController.setLoginLabelAdmin(login);
                    loginSuccessful = true;
                    openEditsWindow();
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
                    mainController.setLoginLabel(login, c);
                    loginSuccessful = true;
                    break;
                }
            }
        }
        if(!loginSuccessful)
        {
            showError("Niepoprawny login lub hasło");
        }
        else
        {
            Stage stage = (Stage) LoginButton.getScene().getWindow();
            stage.close();
        }



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

    public void onSignInButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addNewCustomerPage.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Rejestracja");
            stage.setScene(new Scene(root));
            stage.show();

            Stage loginStage = (Stage) signInButton.getScene().getWindow();
            loginStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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