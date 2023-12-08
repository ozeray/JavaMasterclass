package ayo.fxtodo;

import ayo.fxtodo.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TodoController {
    @FXML
    private ListView<TodoItem> todoListView;

    private List<TodoItem> todoItems;

    public void initialize() {
        TodoItem item1 = new TodoItem("Mail birthday card", "Buy a 30th birthday card to John",
                LocalDate.of(2023, Month.NOVEMBER, 25));
        TodoItem item2 = new TodoItem("Doctor's Appointment", "See Dr. Smith at 123 Main Street. Bring paperwork",
                LocalDate.of(2023, Month.OCTOBER, 23));
        TodoItem item3 = new TodoItem("Finish design proposal for client", "I promised Mike I'd email website mockups by Wednesday 22nd December",
                LocalDate.of(2023, Month.DECEMBER, 22));
        TodoItem item4 = new TodoItem("Pickup Doug at train station", "Doug will be arriving on Friday 24nd November",
                LocalDate.of(2023, Month.DECEMBER, 24));
        TodoItem item5 = new TodoItem("Pickup dry cleaning", "The cloths should be ready by Monday 20th November",
                LocalDate.of(2023, Month.NOVEMBER, 20));

        todoItems = new ArrayList<>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private TextArea itemDetailsTextArea;

    @FXML
    protected void handleClickListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();

        StringBuilder sb = new StringBuilder(item.getDetails());
        sb.append("\n\n\n\n")
                .append("Due: ")
                .append(item.getDeadLine().toString());
        itemDetailsTextArea.setText(sb.toString());
    }
}