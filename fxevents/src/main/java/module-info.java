module ayo.fxevents {
    requires javafx.controls;
    requires javafx.fxml;


    opens ayo.fxevents to javafx.fxml;
    exports ayo.fxevents;
}