module com.storefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires kernel;
    requires layout;
    requires jdk.xml.dom;
    requires javafx.swing;
    requires org.apache.pdfbox;
    requires junit;


    opens com.storefx to javafx.fxml;
    exports com.storefx;
    exports store;
}