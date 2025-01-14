module com.storefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires kernel;
    requires layout;
    requires jdk.xml.dom;


    opens com.storefx to javafx.fxml;
    exports com.storefx;
}