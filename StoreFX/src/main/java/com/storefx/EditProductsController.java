package com.storefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import store.*;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;

public class EditProductsController {

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
        }
    }

    @FXML
    public void initialize() {
        ProductCategoryComboBox.setOnAction(event -> handleCategoryChange());
        AddPhotoButton.setOnAction(event -> selectPhoto());

        // to tu nie moze byc, bo cały czas jest store == null, dopiero pozniej zmienia sie na nie null
//        if (store != null) {
//            ArrayList<Product> products = store.getInventory().getProducts();
//            for (Product product : products) {
//                System.out.println(product.getProductName());
//                AnchorPane productPane = createProductPane(product);
//                productVBox.getChildren().add(productPane);
//            }
//        }
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

        TextField nameTextField = new TextField(product.getProductName());
        nameTextField.setLayoutX(386);
        nameTextField.setLayoutY(21);
        nameTextField.setPrefWidth(180);
        nameTextField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ced4da; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 4;");

        TextField quantityTextField = new TextField(String.valueOf(product.getQuantity()));
        quantityTextField.setLayoutX(386);
        quantityTextField.setLayoutY(52);
        quantityTextField.setPrefWidth(180);
        quantityTextField.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ced4da; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 4;");

        productPane.getChildren().addAll(deleteButton, editButton, imageView, nameLabel, quantityLabel, nameTextField, quantityTextField);

        return productPane;
    }


    private void editProduct(Product product) {

    }

    private void deleteProduct(Product product, AnchorPane productPane) {
        store.getInventory().removeProduct(product);
        productVBox.getChildren().remove(productPane);
    }
}



