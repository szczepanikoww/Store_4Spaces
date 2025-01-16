package com.storefx;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import store.*;


import java.io.FileNotFoundException;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.round;

public class CartPageController {
    @FXML
    public Button goToOrder;
    @FXML
    public VBox cartVbox;
    @FXML
    public Label sumTotal;

    private Map<Product, TextField> quantityInCart = new HashMap<>();

    public Store store;
    public mainPageController mainPageController;
    public Customer aktCustomer;
    Map<Product, Integer> productsInCart;

    public void setStore(Store store) {
        this.store = store;
        System.out.println("Store set in EditProductsController: " + store);
        this.productsInCart = store.getCart().getProductsInCart();
        populateCart();
    }

    public void setAktCustomer(Customer customer) {
        this.aktCustomer = customer;
    }

    public void setMainController(mainPageController mainController) {
        this.mainPageController = mainController;
    }

    private void populateCart() {
        if (cartVbox != null) {
            cartVbox.getChildren().clear();
            for (Map.Entry<Product, Integer> entry : productsInCart.entrySet()) {
                addProductToCart(entry.getKey(), entry.getValue());
            }
            sumTotal.setText(String.format("%.2f PLN", store.getCart().updateTotalPrice()));
        }
    }

    private void addProductToCart(Product product, int quantity) {
            AnchorPane productPane = new AnchorPane();
            productPane.setPrefSize(803, 167);

            ImageView imageView = new ImageView(product.getImage());
            imageView.setFitHeight(143);
            imageView.setFitWidth(146);
            imageView.setLayoutX(21);
            imageView.setLayoutY(9);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(true);

            Label nameLabel = new Label("Nazwa produktu");
            nameLabel.setLayoutX(180);
            nameLabel.setLayoutY(23);

            Label quantityLabel = new Label("Ilość");
            quantityLabel.setLayoutX(180);
            quantityLabel.setLayoutY(64);

            Label priceLabel = new Label("Cena");
            priceLabel.setLayoutX(178);
            priceLabel.setLayoutY(108);

            TextField nameTextField = new TextField(product.getProductName());
            nameTextField.setEditable(false);
            nameTextField.setLayoutX(311);
            nameTextField.setLayoutY(19);

            TextField quantityTextField = new TextField(String.valueOf(quantity));
            quantityTextField.setEditable(false);
            quantityTextField.setLayoutX(311);
            quantityTextField.setLayoutY(60);

            TextField priceTextField = new TextField(String.valueOf(product.getPrice() * quantity));
            priceTextField.setEditable(false);
            priceTextField.setLayoutX(311);
            priceTextField.setLayoutY(104);

            Button removeButton = new Button("Usuń z koszyka");
            removeButton.setLayoutX(681);
            removeButton.setLayoutY(129);
            removeButton.setOnAction(event -> removeFromCart(product));

            Button addButton = new Button("+");
            addButton.setLayoutX(479);
            addButton.setLayoutY(60);
            addButton.setOnAction(event -> updateQuantity(product, 1));

            Button subtractButton = new Button("-");
            subtractButton.setLayoutX(513);
            subtractButton.setLayoutY(60);
            subtractButton.setOnAction(event -> updateQuantity(product, -1));

            productPane.getChildren().addAll(imageView, nameLabel, quantityLabel, priceLabel, nameTextField, quantityTextField, priceTextField, removeButton, addButton, subtractButton);
            quantityInCart.put(product, quantityTextField);
            cartVbox.getChildren().add(productPane);

    }

    private void removeFromCart(Product product) {
        product.setHidden(false);
        product.setProductQuantity(product.getQuantity() + productsInCart.get(product));
        store.getCart().removeProduct(product, productsInCart.get(product));
        store.getCart().updateTotalPrice();
        populateCart();

    }

    private void updateQuantity(Product product, int delta) {
        TextField quantityTextField = quantityInCart.get(product);
        int quantity = Integer.parseInt(quantityTextField.getText());
        if(quantity + delta > 0 && product.getQuantity() >= delta){
            product.setProductQuantity(product.getQuantity() - delta);
            store.getCart().updateProductQuantity(product, delta);
            store.getCart().updateTotalPrice();
            product.setHidden(false);
            populateCart();
        }else if(quantity + delta == 0){
            removeFromCart(product);
        }else {
            showError("Brak wystarczającej ilości produktu " + product.getProductName() + " na stanie");
        }
    }

    public void goToOrder(ActionEvent actionEvent) {
        if(aktCustomer == null){
            showError("Musisz być zalogowany, aby złożyć zamówienie");
            return;
        } else if (aktCustomer.getCustomerAddress() ==null || aktCustomer.getCustomerAddressDetails() == null || aktCustomer.getCustomerEmail() == null || aktCustomer.getCustomerPhoneNumber() == null){
            showError("Uzupełnij dane adresowe w panelu użytkownika, aby złożyc zamówienie");
            return;
        }


        if (store.getCart() != null) {
            Order order;
            if (store.getOrders().isEmpty()) {
                order = new Order(1, aktCustomer, new ArrayList<>(productsInCart.keySet()), store.getCart().getTotalPrice(), new Date());
            } else {
                order = new Order(store.getOrders().size() + 1, aktCustomer, new ArrayList<>(productsInCart.keySet()), store.getCart().getTotalPrice(), new Date());
            }
            store.getOrders().add(order);
            aktCustomer.getPreviousOrders().add(order);
            try {
                generateInvoice(order);
                System.out.println("Invoice generated");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            store.getCart().clearCart();
            showSuccess("Zamówienie zostało złożone pomyślnie");
            populateCart();
            sumTotal.setText("0.00 PLN");
        } else {
            showError("Koszyk jest pusty");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void generateInvoice(Order order) throws FileNotFoundException {
        String dest = "invoice" + order.getId() + ".pdf";
        PdfWriter pdfWriter = new PdfWriter(dest);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);

        float threecol = 190f;
        float twocol = 285f;
        float twocol150 = twocol + 150f;
        float twocolumnWidth[] = {twocol150, twocol};
        float threeColumnWidth[] = {threecol, threecol, threecol};
        float fullwidth[] = {threecol, threecol, threecol};
        Paragraph onesp = new Paragraph("\n");

        Table table = new Table(twocolumnWidth);
        table.addCell(new Cell().add("Faktura").setFontSize(20f).setBorder(Border.NO_BORDER).setBold());
        Table nestedTable = new Table(new float[]{twocol / 2, twocol / 2});
        nestedTable.addCell(getHeaderTextCell("Data:"));
        nestedTable.addCell(getHeaderTextCellValue(order.getDate().toString()));
        nestedTable.addCell(getHeaderTextCell("Numer faktury:"));
        nestedTable.addCell(getHeaderTextCellValue(String.valueOf("FKTR" + order.getId())));

        table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));

        Border border = new SolidBorder(Color.GRAY, 2f);
        Table divider = new Table(fullwidth);
        divider.setBorder(border);

        document.add(table);
        document.add(onesp);
        document.add(divider);
        document.add(onesp);

        Table twoColTable = new Table(twocolumnWidth);
        twoColTable.addCell(getBillingandShippingCell("Billing Information"));
        twoColTable.addCell(getBillingandShippingCell("Shipping Information"));

        document.add(twoColTable.setMarginBottom(12f));

        Table twoColTable2 = new Table(twocolumnWidth);
        twoColTable2.addCell(getCell10fLeft("Firma: ", true));
        twoColTable2.addCell(getCell10fLeft("Imię i nazwisko: ", true));
        twoColTable2.addCell(getCell10fLeft("Store 4Spaces", false));
        twoColTable2.addCell(getCell10fLeft(aktCustomer.getCustomerName() + " " + aktCustomer.getCustomerSurname(), false));

        document.add(twoColTable2);

        Table twoColTable3 = new Table(twocolumnWidth);
        twoColTable3.addCell(getCell10fLeft("Adres: ", true));
        twoColTable3.addCell(getCell10fLeft("Adres: ", true));
        twoColTable3.addCell(getCell10fLeft("30-059 Kraków, al. Adama Mickiewicza 30 ", false));
        twoColTable3.addCell(getCell10fLeft(aktCustomer.getCustomerAddressDetails(), false));

        document.add(twoColTable3);


        Table twoColTable4 = new Table(twocolumnWidth);
        twoColTable4.addCell(getCell10fLeft("Adres email: ", true));
        twoColTable4.addCell(getCell10fLeft("Adres email: ", true));
        twoColTable4.addCell(getCell10fLeft("store4spaces@email.com", false));
        twoColTable4.addCell(getCell10fLeft(aktCustomer.getCustomerEmail(), false));

        document.add(twoColTable4);

        Table twoColTable5 = new Table(twocolumnWidth);
        twoColTable5.addCell(getCell10fLeft("Nr telefonu: ", true));
        twoColTable5.addCell(getCell10fLeft("Nr telefonu: ", true));
        twoColTable5.addCell(getCell10fLeft("+48 515 626 989", false));
        twoColTable5.addCell(getCell10fLeft(aktCustomer.getCustomerPhoneNumber(), false));

        document.add(twoColTable5.setMarginBottom(10f));

        Table tableDivider2 = new Table(fullwidth);
        Border border2 = new DashedBorder(Color.GRAY, 1f);

        document.add(tableDivider2.setBorder(border2));

        Paragraph productsPara = new Paragraph("Products");
        productsPara.setBold();
        document.add(productsPara);

        Table productsTable = new Table(threeColumnWidth);
        productsTable.setBackgroundColor(Color.GRAY, 0.7f);

        productsTable.addCell(new Cell().add("Produkt").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
        productsTable.addCell(new Cell().add("Ilosc").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
        productsTable.addCell(new Cell().add("Cena").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

        try {
            for (Map.Entry<Product, Integer> entry : productsInCart.entrySet()) {
                productsTable.addCell(new Cell().add(entry.getKey().getProductName()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
                productsTable.addCell(new Cell().add(String.valueOf(entry.getValue())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
                productsTable.addCell(new Cell().add(String.valueOf((entry.getKey().getPrice()) * entry.getValue())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        document.add(productsTable.setMarginBottom(20f));

        document.add(tableDivider2.setBorder(border2));

        Table totalTable = new Table(threeColumnWidth);
        productsTable.setBackgroundColor(Color.GRAY, 0.7f);
        totalTable.addCell(new Cell().add("").setBold().setTextAlignment(TextAlignment.RIGHT));
        totalTable.addCell(new Cell().add("Total:").setBold().setTextAlignment(TextAlignment.RIGHT));
        totalTable.addCell(new Cell().add(String.valueOf(store.getCart().getTotalPrice())).setTextAlignment(TextAlignment.RIGHT));
        document.add(totalTable.setBorder(Border.NO_BORDER));

        document.close();

    }

    static Cell getHeaderTextCell(String text) {
        return new Cell().add(text).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getHeaderTextCellValue(String textValue) {
        return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getBillingandShippingCell(String text) {
        return new Cell().add(text).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getCell10fLeft(String text, Boolean isBold) {
        Cell myCell = new Cell().add(text).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        return isBold ? myCell.setBold() : myCell;
    }

    public void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukces");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}