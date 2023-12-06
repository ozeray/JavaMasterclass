package ayo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Path.of("fxdemo/src/ayo/hello-view.fxml").toUri().toURL());

//        GridPane root = new GridPane(0, 20.0);
//        root.setAlignment(Pos.CENTER);
//        Label greeting = new Label("Welcome to JavaFX!");
//        greeting.setTextFill(Color.GREEN);
//        greeting.setFont(new Font("Times New Roman", 70));
//        root.getChildren().add(greeting);

        Scene scene = new Scene(root, 750, 240);
        stage.setTitle("Hello JavaFX!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }
}