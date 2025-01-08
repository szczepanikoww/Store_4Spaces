module com.storefx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.storefx to javafx.fxml;
    exports com.storefx;
}