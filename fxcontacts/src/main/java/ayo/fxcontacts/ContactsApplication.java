package ayo.fxcontacts;

import ayo.fxcontacts.datamodel.ContactData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

public class ContactsApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Path.of("fxcontacts/src/main/resources/ayo/fxcontacts/main-window.fxml").toUri().toURL());
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setTitle("My Contacts");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
        ContactData.getInstance().loadContacts();
    }

    @Override
    public void stop() {
        ContactData.getInstance().saveContacts();
    }
}