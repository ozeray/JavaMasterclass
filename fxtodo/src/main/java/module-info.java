module ayo.fxtodo {
    requires javafx.controls;
    requires javafx.fxml;


    opens ayo.fxtodo to javafx.fxml;
    exports ayo.fxtodo;
}