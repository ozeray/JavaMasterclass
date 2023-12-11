package ayo.fxtodo;

import ayo.fxtodo.datamodel.TodoData;
import ayo.fxtodo.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.web.WebView;

import java.time.LocalDate;

public class DialogController {
    @FXML
    private TextField shortDescriptionField;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadlinePicker;
    @FXML
    private Label deadlineLabel;
    @FXML
    private WebView webView;

    public void initialize() {
        deadlineLabel.setEffect(new DropShadow());
    }

    public TodoItem processResults() {
        String shortDescription = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadline = deadlinePicker.getValue();

        TodoItem item = new TodoItem(shortDescription, details, deadline);
        TodoData.getInstance().addTodoItem(item);
        return item;
    }

    public void mouseEntered() {
        deadlineLabel.setScaleX(2.0);
        deadlineLabel.setScaleY(2.0);
    }

    public void mouseExited() {
        deadlineLabel.setScaleX(1.0);
        deadlineLabel.setScaleY(1.0);
    }

    public void handleGoToGoogle() {
//        HelloApplication.getInstance().getHostServices().showDocument("https://google.com");
        webView.getEngine().load("https://google.com");
    }
}
