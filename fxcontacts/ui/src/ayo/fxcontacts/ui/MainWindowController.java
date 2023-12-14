package ayo.fxcontacts.ui;

import ayo.fxcontacts.common.Contact;
import ayo.fxcontacts.xml.ContactData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.Optional;

class ContactTask extends Task<ObservableList<Contact>> {
    @Override
    protected ObservableList<Contact> call() {
        return FXCollections.observableArrayList(ContactData.getInstance().loadContacts());
    }
}
public class MainWindowController {
    @FXML
    private TableView<Contact> contacts;

    @FXML
    private ProgressBar progressBar;

    public void initialize() {
//        contacts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        contacts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    void populate() {
        ContactTask task = new ContactTask();
        contacts.itemsProperty().bind(task.valueProperty());

        taskStarted(task);
        task.setOnSucceeded(e -> taskCompleted(null));
        task.setOnFailed(e -> taskCompleted(null));

        new Thread(task).start();
    }

    private void taskCompleted(Contact contact) {
        progressBar.setVisible(false);
        if (contact != null) {
            contacts.getSelectionModel().select(contact);
        } else {
            contacts.getSelectionModel().selectFirst();
        }
    }

    private void taskStarted(Task<?> task) {
        progressBar.progressProperty().bind(task.progressProperty());
        progressBar.setVisible(true);
    }

    @FXML
    protected void handleNewContact() {
        FXMLLoader fxmlLoader = getFxmlLoader();
        Dialog<ButtonType> dialog = initializeDialog("Add New Contact",
                "Use this dialog to add new contact", fxmlLoader);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.OK)) {
            ContactFormController controller = fxmlLoader.getController();
            Task<Contact> saveNewTask = new Task<>() {
                @Override
                protected Contact call() {
                    contacts.itemsProperty().unbind();
                    return controller.saveNewContact();
                }
            };
            taskStarted(saveNewTask);

            saveNewTask.setOnSucceeded(event -> {
                contacts.setItems(ContactData.getInstance().getContacts());
                taskCompleted(saveNewTask.getValue());
            });
            saveNewTask.setOnFailed(event -> taskCompleted(null));
            new Thread(saveNewTask).start();
        }
    }

    @FXML
    protected void handleEditContact() {
        Contact selectedContact = contacts.getSelectionModel().getSelectedItem();
        if (selectedContact == null) {
            showSelectContactAlert("Please first select the contact to edit");
            return;
        }
        FXMLLoader fxmlLoader = getFxmlLoader();
        Dialog<ButtonType> dialog = initializeDialog("Edit Contact", "Use this dialog to edit contact", fxmlLoader);
        ContactFormController controller = fxmlLoader.getController();
        controller.populateForm(selectedContact);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.OK)) {
            Task<Void> updateTask = new Task<>() {
                @Override
                protected Void call() {
                    contacts.itemsProperty().unbind();
                    controller.updateContact(selectedContact);
                    return null;
                }
            };
            taskStarted(updateTask);

            updateTask.setOnSucceeded(event -> {
                contacts.setItems(ContactData.getInstance().getContacts());
                contacts.refresh();
                taskCompleted(selectedContact);
            });
            updateTask.setOnFailed(event -> taskCompleted(selectedContact));
            new Thread(updateTask).start();
        }
    }

    private static void showSelectContactAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Oops");
        alert.setHeaderText("Select contact");
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Dialog<ButtonType> initializeDialog(String title, String headerText, FXMLLoader fxmlLoader) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(contacts.getScene().getWindow());
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dialog;
    }

    private FXMLLoader getFxmlLoader() {
        try {
            return new FXMLLoader(Path.of("fxcontacts/ui/resources/ayo/fxcontacts/contact-form.fxml").toUri().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    protected void handleDeleteContact() {
        Contact selectedContact = contacts.getSelectionModel().getSelectedItem();
        if (selectedContact == null) {
            showSelectContactAlert("Please first select the contact to delete");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Contact");
        alert.setHeaderText("Delete contact: " + selectedContact.getFullName());
        alert.setContentText("Are you sure you want to delete the contact?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.OK)) {
            Task<Void> deleteTask = new Task<>() {
                @Override
                protected Void call() {
                    contacts.itemsProperty().unbind();
                    ContactData.getInstance().deleteContact(selectedContact);
                    return null;
                }
            };
            taskStarted(deleteTask);

            deleteTask.setOnSucceeded(event -> {
                contacts.setItems(ContactData.getInstance().getContacts());
                contacts.refresh();
                taskCompleted(null);
            });
            deleteTask.setOnFailed(event -> taskCompleted(selectedContact));
            new Thread(deleteTask).start();
        }
    }

    @FXML
    protected void handleExit() {
        Platform.exit();
    }
}