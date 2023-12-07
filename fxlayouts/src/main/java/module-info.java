module ayo.layouts {
    requires javafx.controls;
    requires javafx.fxml;


    opens ayo.layouts to javafx.fxml;
    exports ayo.layouts;
}