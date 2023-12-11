package ayo.fxtodo;

import ayo.fxtodo.datamodel.TodoData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

public class TodoApplication extends Application {
    private static TodoApplication todoApplication;

    public static TodoApplication getInstance() {
        return todoApplication;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Path.of("fxtodo/src/main/resources/ayo/fxtodo/main-window.fxml").toUri().toURL());
//        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setTitle("Todo List");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
        try {
            TodoData.getInstance().loadToDoItems();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        todoApplication = this;
    }

    @Override
    public void stop() {
        try {
            TodoData.getInstance().storeTodoItems();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}