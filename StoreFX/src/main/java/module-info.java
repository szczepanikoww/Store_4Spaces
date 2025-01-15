module com.storefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires kernel;
    requires layout;
    requires jdk.xml.dom;
    requires javafx.swing;


    opens com.storefx to javafx.fxml;
    exports com.storefx;
}