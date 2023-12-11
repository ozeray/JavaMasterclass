module ayo.fxcontacts {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;

    opens ayo.fxcontacts to javafx.fxml;
    opens ayo.fxcontacts.datamodel to javafx.base;
    exports ayo.fxcontacts;
}