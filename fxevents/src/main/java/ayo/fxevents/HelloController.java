package ayo.fxevents;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField greeting;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Label myLabel;

    // Will be called automatically
    @FXML
    public void initialize() {
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }

    @FXML
    private void onHelloButtonClick(ActionEvent e) {
        if (e.getSource().equals(helloButton)) {
            System.out.println("Hello, " + greeting.getText() + ", " + e.getSource());
            Task<String> task0 = new Task<>() {
                @Override
                protected String call() throws Exception {
                    updateMessage("Starting to sleep");
                    Thread.sleep(10000);
                    updateMessage("Finished sleeping");
                    return null;
                }
            };
            new Thread(task0).start();
            myLabel.textProperty().bind(task0.messageProperty());
//            Runnable task = () -> {
//                try {
//                    String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background thread";
//                    System.out.println("Sleeping in " + s);
//                    Thread.sleep(10000);
//
//                    Platform.runLater(() -> {
//                        String s2 = Platform.isFxApplicationThread() ? "UI Thread" : "Background thread";
//                        System.out.println("Doing in " + s2);
//                        myLabel.setText("We did something!");
//                    });
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
//            };
//            new Thread(task).start();

            if (checkBox.isSelected()) {
                greeting.clear();
                helloButton.setDisable(true);
                byeButton.setDisable(true);
            }
        } else if (e.getSource().equals(byeButton)) {
            System.out.println("Bye, " + greeting.getText() + ", " + e.getSource());
        } else {
            System.out.println("Unknown source");
        }
    }

    public void handleKeyRelease() {
        String text = greeting.getText();
        boolean btnDisabled = text.isEmpty() || text.trim().isEmpty();
        helloButton.setDisable(btnDisabled);
        byeButton.setDisable(btnDisabled);
    }

    @FXML
    private void handleChange() {
        boolean selected = checkBox.isSelected();
        System.out.println("State changed: " + (selected ? "checked" : "unchecked"));
    }

}