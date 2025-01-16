package com.storefx;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.layout.Document;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import store.Customer;
import store.Order;
import store.Store;
import javafx.embed.swing.SwingNode;
import java.awt.Dimension;
import java.awt.Desktop;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PreviousOrdersController {
    @FXML
    private VBox orderVbox;

    public Store store;
    public mainPageController mainPageController;
    public CartPageController cartPageController;
    public Customer aktCustomer;

    public void setMainController(mainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    public void setStore(Store store, Customer customer) {
        this.store = store;
        this.aktCustomer = customer;
        ArrayList<Order> orders = aktCustomer.getPreviousOrders();
        for (Order order : orders) {
            addOrderToVBox(order);
        }
    }

    private void addOrderToVBox(Order order) {
        AnchorPane orderPane = new AnchorPane();
        orderPane.setPrefSize(841, 145);

        Label dateLabel = new Label("Data zamówienia");
        dateLabel.setLayoutX(39);
        dateLabel.setLayoutY(29);
        dateLabel.setFont(new Font(14));

        Label amountLabel = new Label("Kwota zamówienia");
        amountLabel.setLayoutX(39);
        amountLabel.setLayoutY(63);
        amountLabel.setFont(new Font(14));

        LocalDateTime orderDate = order.getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Label dateField = new Label(orderDate.format(formatter));
        dateField.setLayoutX(204);
        dateField.setLayoutY(27);
        dateField.setStyle("-fx-font-weight: bold;");

        Label amountField = new Label(String.format("%.2f", order.getTotalPrice()));
        amountField.setLayoutX(204);
        amountField.setLayoutY(61);
        amountField.setStyle("-fx-font-weight: bold;");

        Button invoiceButton = new Button("Pobierz Fakturę");
        invoiceButton.setLayoutX(690);
        invoiceButton.setLayoutY(94);
        invoiceButton.setFont(new Font(14));
        invoiceButton.setOnAction(event -> {
               String path = "invoice" + order.getId() + ".pdf";
               openPdfInWindow(path);
        });

        orderPane.getChildren().addAll(dateLabel, amountLabel, dateField, amountField, invoiceButton);
        orderVbox.getChildren().add(orderPane);
    }

    public void openPdfInWindow(String pdfFilePath) {
        try {
            File pdfFile = new File(pdfFilePath);
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Desktop is not supported. Cannot open PDF.");
                }
            } else {
                System.out.println("File does not exist: " + pdfFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}