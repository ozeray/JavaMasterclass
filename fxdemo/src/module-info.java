module ayo.fxdemo {
    requires javafx.controls;
    requires javafx.fxml;

    opens ayo to javafx.fxml;
    exports ayo;
}