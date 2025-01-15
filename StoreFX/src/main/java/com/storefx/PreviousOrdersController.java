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

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

        TextField dateField = new TextField(order.getOrderDate().toString());
        dateField.setLayoutX(204);
        dateField.setLayoutY(27);

        TextField amountField = new TextField(String.valueOf(order.getTotalPrice()));
        amountField.setLayoutX(204);
        amountField.setLayoutY(61);

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
        Stage stage = new Stage();
        SwingNode swingNode = new SwingNode();

        createAndSetSwingContent(swingNode, pdfFilePath);

        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);

        stage.setScene(new Scene(pane, 800, 600));
        stage.setTitle("Invoice PDF");
        stage.show();
    }

    public void createAndSetSwingContent(final SwingNode swingNode, String pdfFilePath) {
        SwingUtilities.invokeLater(() -> {
            try {
                PDDocument document = PDDocument.load(new File(pdfFilePath));
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 150); // Lower DPI to reduce size
                ImageIcon imageIcon = new ImageIcon(bufferedImage);
                JLabel label = new JLabel(imageIcon);
                label.setPreferredSize(new Dimension(2000, 2000)); // Set preferred size
                JScrollPane scrollPane = new JScrollPane(label);
                swingNode.setContent(scrollPane);
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}