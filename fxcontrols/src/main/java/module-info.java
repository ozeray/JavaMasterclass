module ayo.fxcontrols {
    requires javafx.controls;
    requires javafx.fxml;


    opens ayo.fxcontrols to javafx.fxml;
    exports ayo.fxcontrols;
}