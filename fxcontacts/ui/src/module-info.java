module ui {
    requires xml;
    requires javafx.fxml;
    requires javafx.controls;

    exports ayo.fxcontacts.ui to javafx.graphics;
    opens ayo.fxcontacts.ui to javafx.fxml;
}