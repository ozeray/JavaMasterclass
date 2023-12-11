module ayo.fxtodo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens ayo.fxtodo to javafx.fxml;
    exports ayo.fxtodo;
}