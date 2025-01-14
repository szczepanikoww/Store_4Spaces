package com.storefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import store.*;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.security.cert.PolicyNode;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditProductsController {
    public EditSingleProduct editSingleProduct;
    public Store store;

    @FXML
    public Label OSLabel, ParametryLabel, StorageLabel, RAMLabel, ScreenLabel, ProcessorLabel,
                BatteryLabel, LaptopGraphicCard, PhoneAparatLabel;
    @FXML
    public TextField LaptopGraphicCardTextField, PhoneApartTextField, ProductNameTextField,
                    ProductPriceTextField, ProductQuantityTextField, ProductBrandTextField,
                    ProductOSTextField, ProductStorageTextField, ProductScreenTextField,
                    ProductProcessorTextField, ProductBatteryTextField, ProductRamTextField;
    @FXML
    public ImageView productImageView;

    @FXML
    public Button AddPhotoButton, SaveProductButton, CancelAddingProductButton;

    @FXML
    public File selectedImageFile;

    @FXML
    public TextArea ProductDescriptionTextArea;

    @FXML
    public ComboBox<String> ProductCategoryComboBox;

    @FXML
    public VBox productVBox;

    public LoginPageController loginController;
    @FXML
    public TextField quantityTextField, cenaTextField;
    @FXML
    public VBox usersVBox;

    private Map<Product, TextField> quantityTextFieldMap = new HashMap<>();
    private Map<Product, TextField> cenaTextFieldMap = new HashMap<>();
    private Map<Product, ImageView> imageViewMap = new HashMap<>();



    // to przekazuje store z loginController do EditProductsController
    public void setStore(Store store) {
        this.store = store;
        System.out.println("Store set in EditProductsController: " + store);
        if (store != null) {
            ArrayList<Product> products = store.getInventory().getProducts();
            for (Product product : products) {
                AnchorPane productPane = createProductPane(product);
                productVBox.getChildren().add(productPane);
            }

            ArrayList<Customer> customers = store.getCustomers();
            for (Customer customer : customers) {
                AnchorPane userPane = createUserPane(customer);
                usersVBox.getChildren().add(userPane);
            }
        }
    }

    @FXML
    public void initialize() {
        ProductCategoryComboBox.setOnAction(event -> handleCategoryChange());
        AddPhotoButton.setOnAction(event -> selectPhoto());
    }

    @FXML
    public void setMainController(LoginPageController loginController) {
        this.loginController = loginController;
    }

    @FXML
    private void handleCategoryChange() {
        String selectedCategory = ProductCategoryComboBox.getValue();
        if("Inny".equals(selectedCategory)){
            OSLabel.setVisible(false);
            ParametryLabel.setVisible(false);
            StorageLabel.setVisible(false);
            RAMLabel.setVisible(false);
            ScreenLabel.setVisible(false);
            ProcessorLabel.setVisible(false);
            BatteryLabel.setVisible(false);

            ProductOSTextField.setVisible(false);
            ProductStorageTextField.setVisible(false);
            ProductRamTextField.setVisible(false);
            ProductScreenTextField.setVisible(false);
            ProductProcessorTextField.setVisible(false);
            ProductBatteryTextField.setVisible(false);

            PhoneAparatLabel.setVisible(false);
            PhoneApartTextField.setVisible(false);
            LaptopGraphicCard.setVisible(false);
            LaptopGraphicCardTextField.setVisible(false);
        } else{
            OSLabel.setVisible(true);
            ParametryLabel.setVisible(true);
            StorageLabel.setVisible(true);
            RAMLabel.setVisible(true);
            ScreenLabel.setVisible(true);
            ProcessorLabel.setVisible(true);
            BatteryLabel.setVisible(true);

            ProductOSTextField.setVisible(true);
            ProductStorageTextField.setVisible(true);
            ProductRamTextField.setVisible(true);
            ProductScreenTextField.setVisible(true);
            ProductProcessorTextField.setVisible(true);
            ProductBatteryTextField.setVisible(true);

            if ("Telefon".equals(selectedCategory)) {
                PhoneAparatLabel.setVisible(true);
                PhoneApartTextField.setVisible(true);
                LaptopGraphicCard.setVisible(false);
                LaptopGraphicCardTextField.setVisible(false);
            } else if ("Laptop".equals(selectedCategory)) {
                PhoneAparatLabel.setVisible(false);
                PhoneApartTextField.setVisible(false);
                LaptopGraphicCard.setVisible(true);
                LaptopGraphicCardTextField.setVisible(true);
            } else {
                PhoneAparatLabel.setVisible(false);
                PhoneApartTextField.setVisible(false);
                LaptopGraphicCard.setVisible(false);
                LaptopGraphicCardTextField.setVisible(false);
            }
        }
    }

    @FXML
    private void selectPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        selectedImageFile = fileChooser.showOpenDialog(null);
        if (selectedImageFile != null) {
            productImageView.setImage(new Image(selectedImageFile.toURI().toString()));
        }
    }

    @FXML
    public void onSaveProductButton(ActionEvent actionEvent) {
        String selectedCategory = ProductCategoryComboBox.getValue();

        if ("Telefon".equals(selectedCategory)) {
            try {
                Phone phone = new Phone();
                phone.setPhoneName(ProductNameTextField.getText());
                phone.setPhonePrice(Double.parseDouble(ProductPriceTextField.getText()));
                phone.setPhoneQuantity(Integer.parseInt(ProductQuantityTextField.getText()));
                phone.setPhoneBrand(ProductBrandTextField.getText());
                phone.setPhoneDescription(ProductDescriptionTextArea.getText());

                phone.setPhoneOS(ProductOSTextField.getText());
                phone.setPhoneProcessor(ProductProcessorTextField.getText());
                phone.setPhoneRAM(Integer.parseInt(ProductRamTextField.getText()));
                phone.setPhoneStorage(Integer.parseInt(ProductStorageTextField.getText()));
                phone.setPhoneDisplay(ProductScreenTextField.getText());
                phone.setPhoneBattery(ProductBatteryTextField.getText());

                phone.setPhoneCamera(PhoneApartTextField.getText());
                System.out.println("Utworzono telefon bez zdjęcia");

                if (selectedImageFile != null) {
                    phone.setImage(new Image(selectedImageFile.toURI().toString()));
                    System.out.println("Utworzono telefon z zdjęciem");
                }

                store.getInventory().addProduct(phone);

                try {
                    AnchorPane productPane = createProductPane(phone);
                    productVBox.getChildren().add(productPane);
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Nie udało się dodać produktu do listy");
                }

                // Komunikat o sukcesie
                showSuccess("Produkt został pomyślnie dodany.");
                clearForm();

            } catch (Exception e) {
                showError("Coś poszło nie tak, sprawdź dane i spróbuj ponownie");
            }
        } else if ("Laptop".equals(selectedCategory)) {
            try {
                Laptop laptop = new Laptop();
                laptop.setProductName(ProductNameTextField.getText());
                laptop.setProductPrice(Double.parseDouble(ProductPriceTextField.getText()));
                laptop.setProductQuantity(Integer.parseInt(ProductQuantityTextField.getText()));
                laptop.setProductBrand(ProductBrandTextField.getText());

                laptop.setLaptopOS(ProductOSTextField.getText());
                laptop.setLaptopProcessor(ProductProcessorTextField.getText());
                laptop.setLaptopRAM(Integer.parseInt(ProductRamTextField.getText()));
                laptop.setLaptopStorage(Integer.parseInt(ProductStorageTextField.getText()));
                laptop.setLaptopDisplay(ProductScreenTextField.getText());
                laptop.setLaptopBattery(ProductBatteryTextField.getText());

                laptop.setLaptopGraphics(LaptopGraphicCardTextField.getText());

                if (selectedImageFile != null) {
                    laptop.setImage(new Image(selectedImageFile.toURI().toString()));
                }

                store.getInventory().addProduct(laptop);

                AnchorPane productPane = createProductPane(laptop);
                productVBox.getChildren().add(productPane);

                // Komunikat o sukcesie
                showSuccess("Produkt został pomyślnie dodany.");
                clearForm();

            } catch (Exception e) {
                showError("Coś poszło nie tak, sprawdź dane i spróbuj ponownie");
            }
        } else if ("Tablet".equals(selectedCategory)) {
            try {
                Tablet tablet = new Tablet();
                tablet.setProductName(ProductNameTextField.getText());
                tablet.setProductPrice(Double.parseDouble(ProductPriceTextField.getText()));
                tablet.setProductQuantity(Integer.parseInt(ProductQuantityTextField.getText()));
                tablet.setProductBrand(ProductBrandTextField.getText());

                tablet.setTabletOS(ProductOSTextField.getText());
                tablet.setTabletProcessor(ProductProcessorTextField.getText());
                tablet.setTabletRAM(Integer.parseInt(ProductRamTextField.getText()));
                tablet.setTabletStorage(Integer.parseInt(ProductStorageTextField.getText()));
                tablet.setTabletScreenSize(Double.parseDouble(ProductScreenTextField.getText()));
                tablet.setTabletBattery(ProductBatteryTextField.getText());

                if (selectedImageFile != null) {
                    tablet.setImage(new Image(selectedImageFile.toURI().toString()));
                }

                store.getInventory().addProduct(tablet);

                AnchorPane productPane = createProductPane(tablet);
                productVBox.getChildren().add(productPane);

                // Komunikat o sukcesie
                showSuccess("Produkt został pomyślnie dodany.");
                clearForm();

            } catch (Exception e) {
                showError("Coś poszło nie tak, sprawdź dane i spróbuj ponownie");
            }
        } else {
            try {
                String name = ProductNameTextField.getText();
                double price = Double.parseDouble(ProductPriceTextField.getText());
                int quantity = Integer.parseInt(ProductQuantityTextField.getText());
                String brand = ProductBrandTextField.getText();
                Product product = new Product(name, price, quantity, brand);

                if (selectedImageFile != null) {
                    product.setImage(new Image(selectedImageFile.toURI().toString()));
                }

                store.getInventory().addProduct(product);

                AnchorPane productPane = createProductPane(product);
                productVBox.getChildren().add(productPane);
                // Komunikat o sukcesie
                showSuccess("Produkt został pomyślnie dodany.");
                clearForm();

            } catch (Exception e) {
                showError("Coś poszło nie tak, sprawdź dane i spróbuj ponownie");
            }
        }

    }

    public void clearForm(){
        if(selectedImageFile != null){
            selectedImageFile = null;
            productImageView.setImage(null);
        }

        ProductNameTextField.clear();
        ProductPriceTextField.clear();
        ProductQuantityTextField.clear();
        ProductBrandTextField.clear();
        ProductOSTextField.clear();
        ProductStorageTextField.clear();
        ProductRamTextField.clear();
        ProductScreenTextField.clear();
        ProductProcessorTextField.clear();
        ProductBatteryTextField.clear();

        if(ProductCategoryComboBox.getValue().equals("Phone")){
            PhoneApartTextField.clear();
        } else if(ProductCategoryComboBox.getValue().equals("Laptop")){
            LaptopGraphicCardTextField.clear();
        }

        ProductCategoryComboBox.setValue(null);
    }
    public void onCancelAddingProductButton(ActionEvent actionEvent) {
        clearForm();
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
        alert.setTitle("Sukces!!!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public AnchorPane createProductPane(Product product) {
        AnchorPane productPane = new AnchorPane();
        productPane.setPrefSize(800, 200);
        productPane.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #dee2e6; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-radius: 5;");

        Button deleteButton = new Button("Usuń produkt");
        deleteButton.setLayoutX(595);
        deleteButton.setLayoutY(149);
        deleteButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-size: 14; -fx-border-radius: 5; -fx-background-radius: 5;");
        deleteButton.setOnAction(event -> deleteProduct(product, productPane));

        Button editButton = new Button("Edytuj");
        editButton.setLayoutX(705);
        editButton.setLayoutY(149);
        editButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14; -fx-border-radius: 5; -fx-background-radius: 5;");
        editButton.setOnAction(event -> editProduct(product));

        Button addOneButton = new Button("+");
        addOneButton.setLayoutX(620);
        addOneButton.setLayoutY(100);
        addOneButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 14; -fx-border-radius: 5; -fx-background-radius: 5;");
        addOneButton.setOnAction(event -> addOne(product));

        Button removeOneButton = new Button("-");
        removeOneButton.setLayoutX(660);

        removeOneButton.setLayoutY(100);
        removeOneButton.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black; -fx-font-size: 14; -fx-border-radius: 5; -fx-background-radius: 5;");
        removeOneButton.setOnAction(event -> removeOne(product));

        ImageView imageView = new ImageView(product.getImage());
        imageView.setFitHeight(150);
        imageView.setFitWidth(200);
        imageView.setLayoutX(24);
        imageView.setLayoutY(25);
        imageView.setStyle("-fx-border-color: #dee2e6; -fx-border-width: 1; -fx-border-radius: 5;");

        Label nameLabel = new Label("Nazwa produktu");
        nameLabel.setLayoutX(200);
        nameLabel.setLayoutY(25);
        nameLabel.setStyle("-fx-font-size: 16; -fx-text-fill: #495057;");

        Label quantityLabel = new Label("Ilość na magazynie");
        quantityLabel.setLayoutX(200);
        quantityLabel.setLayoutY(56);
        quantityLabel.setStyle("-fx-font-size: 16; -fx-text-fill: #495057;");

        Label cenaLabel = new Label("Cena");
        nameLabel.setLayoutX(200);
        nameLabel.setLayoutY(120);
        nameLabel.setStyle("-fx-font-size: 16; -fx-text-fill: #495057;");

        TextField nameTextField = new TextField(product.getProductName());
        nameTextField.setLayoutX(386);
        nameTextField.setLayoutY(21);
        nameTextField.setPrefWidth(180);
        nameTextField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ced4da; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 4;-fx-editable: false;");

        TextField quantityTextField = new TextField(String.valueOf(product.getQuantity()));
        quantityTextField.setId("quantityTextField");
        quantityTextField.setLayoutX(386);
        quantityTextField.setLayoutY(52);
        quantityTextField.setPrefWidth(180);
        quantityTextField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ced4da; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 4; -fx-editable: false;");

        TextField cenaTextField = new TextField(String.valueOf(product.getPrice()));
        cenaTextField.setId("cenaTextField");
        quantityTextField.setLayoutX(386);
        quantityTextField.setLayoutY(83);
        quantityTextField.setPrefWidth(180);
        quantityTextField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ced4da; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 4; -fx-editable: false;");

        productPane.getChildren().addAll(deleteButton, editButton, addOneButton, removeOneButton, imageView, nameLabel, quantityLabel, nameTextField, quantityTextField, cenaTextField, cenaLabel);
        quantityTextFieldMap.put(product, quantityTextField);
        cenaTextFieldMap.put(product, cenaTextField);
        imageViewMap.put(product, imageView);
        return productPane;
    }


    public AnchorPane createUserPane(Customer customer) {
        AnchorPane userPane = new AnchorPane();
        userPane.setPrefSize(900, 100);
        userPane.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #dee2e6; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-radius: 5;");

        Label nameLabel = new Label("Imię");
        nameLabel.setLayoutX(50);
        nameLabel.setLayoutY(25);
        nameLabel.setStyle("-fx-font-size: 16; -fx-text-fill: #495057;");

        TextField nameTextField = new TextField(customer.getCustomerName());
        nameTextField.setLayoutX(50);
        nameTextField.setLayoutY(50);
        nameTextField.setPrefWidth(180);
        nameTextField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ced4da; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 4;");

        Label surnameLabel = new Label("Nazwisko");
        surnameLabel.setLayoutX(250);
        surnameLabel.setLayoutY(25);
        surnameLabel.setStyle("-fx-font-size: 16; -fx-text-fill: #495057;");

        TextField surnameTextField = new TextField(customer.getCustomerSurname());
        surnameTextField.setLayoutX(250);
        surnameTextField.setLayoutY(50);
        surnameTextField.setPrefWidth(180);
        surnameTextField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ced4da; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 4;");

        Label usernameLabel = new Label("Nazwa użytkownika");
        usernameLabel.setLayoutX(450);
        usernameLabel.setLayoutY(25);
        usernameLabel.setStyle("-fx-font-size: 16; -fx-text-fill: #495057;");

        TextField usernameTextField = new TextField(customer.getLogin());
        usernameTextField.setLayoutX(450);
        usernameTextField.setLayoutY(50);
        usernameTextField.setPrefWidth(180);
        usernameTextField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ced4da; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 4;");

        Button deleteButton = new Button("Usuń użytkownika");
        deleteButton.setLayoutX(700);
        deleteButton.setLayoutY(25);
        deleteButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-size: 14; -fx-border-radius: 5; -fx-background-radius: 5;");
        deleteButton.setOnAction(event -> deleteUser(customer, userPane));

        userPane.getChildren().addAll(nameLabel, surnameLabel, usernameLabel, nameTextField, surnameTextField, usernameTextField, deleteButton);
        return userPane;
    }

    private void deleteUser(Customer customer, AnchorPane userPane) {
        store.getCustomers().remove(customer);
        usersVBox.getChildren().remove(userPane);
    }

    private void removeOne(Product product) {
        store.getInventory().updateProductQuantity(product, -1);
        refreshQuantity(product);
    }

    private void addOne(Product product) {
        store.getInventory().updateProductQuantity(product, 1);
        refreshQuantity(product);
    }


    private void editProduct(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editSingleProduct.fxml"));
            Parent root = loader.load();

            EditSingleProduct controller = loader.getController();
            controller.setProduct(product);
            controller.setStore(store);
            controller.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Edytuj produkt");
            stage.setScene(new Scene(root));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void updateProductPane(Product product) {
    TextField quantityTextField = quantityTextFieldMap.get(product);
    TextField cenaTextField = cenaTextFieldMap.get(product);
    ImageView imageView = imageViewMap.get(product);
    if (quantityTextField != null) {
        quantityTextField.setText(String.valueOf(product.getQuantity()));
    }
    if (cenaTextField != null) {
        cenaTextField.setText(String.valueOf(product.getPrice()));
    }
    if(imageView != null){
        imageView.setImage(product.getImage());
    }
}

    private void deleteProduct(Product product, AnchorPane productPane) {
        store.getInventory().removeProduct(product);
        productVBox.getChildren().remove(productPane);
    }
    
    public void refreshQuantity(Product product){
        TextField quantityTextField = quantityTextFieldMap.get(product);
        quantityTextField.setText(String.valueOf(product.getQuantity()));
    }
}



