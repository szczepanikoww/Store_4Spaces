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

import javafx.scene.control.TextField;
import org.w3c.dom.html.HTMLBaseElement;
import store.Customer;
import store.Order;
import store.Product;
import store.Store;


import java.io.FileNotFoundException;
import java.util.Date;

public class CartPageController {
    @FXML
    public Button goToOrder;

    public Store store;
    public mainPageController mainPageController;
    public Customer aktCustomer;

    public void setStore(Store store) {
        this.store = store;
    }

    public void setMainController(mainPageController mainController) {
        this.mainPageController = mainController;
    }

    public void addProductToCart(String productName, int quantity) {
        store.getCart().addProduct(productName, quantity);
    }

    public void goToOrder(ActionEvent actionEvent) {
        showSuccess("Zamówienie zostało złożone pomyślnie");
        Order order = new Order(1, store.getCustomers().get(0), store.getCart().getProducts(), store.getCart().getTotalPrice(), new Date());
        store.getOrders().add(order);
        aktCustomer.getPreviousOrders().add(order);
        generateInvoice(order);
        store.getCart().clearCart();
    }

    public void generateInvoice(Order order) throws FileNotFoundException {
        String dest = "invoice.pdf";
        PdfWriter pdfWriter = new PdfWriter(dest);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);

        float threecol=190f;
        float twocol=285f;
        float twocol150 = twocol + 150f;
        float twocolumnWidth[] = {twocol150, twocol};
        float threeColumnWidth[] = {threecol, threecol, threecol};
        float fullwidth[]={threecol+3};
        Paragraph onesp = new Paragraph("\n");

        Table table = new Table(twocolumnWidth);
        table.addCell(new Cell().add("Faktura").setFontSize(20f).setBorder(Border.NO_BORDER).setBold());
        Table nestedTable = new Table(new float[]{twocol/2, twocol/2});
        nestedTable.addCell(getHeaderTextCell("Data:"));
        nestedTable.addCell(getHeaderTextCellValue(order.getDate().toString()));
        nestedTable.addCell(getHeaderTextCell("Numer faktury:"));
        nestedTable.addCell(getHeaderTextCellValue(String.valueOf("FKTR" + order.getId())));

        table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));

        Border border = new SolidBorder(Color.GRAY,2f);
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
        twoColTable2.addCell(getCell10fLeft("Store 4Spaces", false));
        twoColTable2.addCell(getCell10fLeft("Imię i nazwisko: ", true));
        twoColTable2.addCell(getCell10fLeft(, false));

        document.add(twoColTable2);

        Table twoColTable3 = new Table(twocolumnWidth);
        twoColTable3.addCell(getCell10fLeft("Adres: ", true));
        twoColTable3.addCell(getCell10fLeft("30-059 Kraków, al. Adama Mickiewicza 30 ", false));
        twoColTable3.addCell(getCell10fLeft("Adres: ", true));
        twoColTable3.addCell(getCell10fLeft(, false));

        document.add(twoColTable3);


        Table twoColTable4 = new Table(twocolumnWidth);
        twoColTable4.addCell(getCell10fLeft("Adres email: ", true));
        twoColTable4.addCell(getCell10fLeft("store4spaces@email.com", false));
        twoColTable4.addCell(getCell10fLeft("Adres email: ", true));
        twoColTable4.addCell(getCell10fLeft(, false));

        document.add(twoColTable4);

        Table twoColTable5 = new Table(twocolumnWidth);
        twoColTable5.addCell(getCell10fLeft("Nr telefonu: ", true));
        twoColTable5.addCell(getCell10fLeft("+48 515 626 989", false));
        twoColTable5.addCell(getCell10fLeft("Nr telefonu: ", true));
        twoColTable5.addCell(getCell10fLeft(, false));

        document.add(twoColTable5.setMarginBottom(10f));

        Table tableDivider2 = new Table(fullwidth);
        Border border2 = new DashedBorder(Color.GRAY,1f);

        document.add(tableDivider2.setBorder(border2));

        Paragraph productsPara = new Paragraph("Products");
        productsPara.setBold();
        document.add(productsPara);

        Table productsTable = new Table(threeColumnWidth);
        productsTable.setBackgroundColor(Color.GRAY, 0.7f);

        productsTable.addCell(new Cell().add("Produkt").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
        productsTable.addCell(new Cell().add("Ilość").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
        productsTable.addCell(new Cell().add("Cena").setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));


        for (Product product : store.getCart().getProducts()){
            productsTable.addCell(new Cell().add(product.getProductName()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
            productsTable.addCell(new Cell().add("1").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
            productsTable.addCell(new Cell().add(String.valueOf(product.getPrice())).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
        }


        document.close();

    }

    static Cell getHeaderTextCell(String text){
        return new Cell().add(text).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getHeaderTextCellValue(String textValue){
        return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getBillingandShippingCell(String text){
        return new Cell().add(text).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getCell10fLeft(String text, Boolean isBold){
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