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

public class EditProductsController {

    @FXML
    public ComboBox<String> ProductCategoryComboBox;

    @FXML
    public TextField LaptopGraphicCardTextField, PhoneApartTextField, ProductNameTextField, ProductPriceTextField;

    @FXML
    public Label LaptopGraphicCard, PhoneAparatLabel;

    @FXML
    public ImageView productImageView;
    public Button AddPhotoButton;
    public File selectedImageFile;
    public TextField ProductQuantityTextField;
    public TextField ProductBrandTextField;
    public TextField ProductOSTextField;
    public TextField ProductStorageTextField;
    public TextField ProductScreenTextField;
    public TextField ProductProcessorTextField;
    public TextField ProductBatteryTextField;
    public Button SaveProductButton;
    public Button CancelAddingProductButton;
    public TextField ProductRamTextField;
    public Label OSLabel;
    public Label ParametryLabel;
    public Label StorageLabel;
    public Label RAMLabel;
    public Label ScreenLabel;
    public Label ProcessorLabel;
    public Label BatteryLabel;
    public TextArea ProductDescriptionTextArea;

    @FXML
    private VBox productVBox;

    private Store store;
    private LoginPageController loginController;

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
            // Assuming you have an ImageView named productImageView to display the selected image
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

                // Dodaj produkt do listy i wyświetl
                // products.add(phone);
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

                // Dodaj produkt do listy i wyświetl
                // productList.add(laptop);
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

                // Dodaj produkt do listy i wyświetl
                // productList.add(tablet);

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

//                // Dodaj produkt do listy i wyświetl
//                productList.add(product);

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
        productPane.setPrefSize(200, 200);

        Button deleteButton = new Button("Usuń produkt");
        deleteButton.setLayoutX(595);
        deleteButton.setLayoutY(149);
        deleteButton.setOnAction(event -> deleteProduct(product, productPane));

        Button editButton = new Button("Edytuj");
        editButton.setLayoutX(705);
        editButton.setLayoutY(149);
        editButton.setOnAction(event -> editProduct(product));

        ImageView imageView = new ImageView(product.getImage());
        imageView.setFitHeight(150);
        imageView.setFitWidth(200);
        imageView.setLayoutX(24);
        imageView.setLayoutY(25);

        Label nameLabel = new Label("Nazwa produktu");
        nameLabel.setLayoutX(260);
        nameLabel.setLayoutY(25);

        Label quantityLabel = new Label("Ilość na magazynie");
        quantityLabel.setLayoutX(260);
        quantityLabel.setLayoutY(56);

        TextField nameTextField = new TextField(product.getProductName());
        nameTextField.setLayoutX(386);
        nameTextField.setLayoutY(21);

        TextField quantityTextField = new TextField(String.valueOf(product.getQuantity()));
        quantityTextField.setLayoutX(386);
        quantityTextField.setLayoutY(52);

        productPane.getChildren().addAll(deleteButton, editButton, imageView, nameLabel, quantityLabel, nameTextField, quantityTextField);

        return productPane;
    }

    private void editProduct(Product product) {
    }

    private void deleteProduct(Product product, AnchorPane productPane) {
    }
}



