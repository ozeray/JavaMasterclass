package ayo.fxevents;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField greeting;

    @FXML
    private void onHelloButtonClick(ActionEvent e) {
        System.out.println("Hello, " + greeting.getText() + ", " + e.getSource());
    }
}