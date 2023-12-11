package ayo.fxcontacts;

import ayo.fxcontacts.datamodel.Contact;
import ayo.fxcontacts.datamodel.ContactData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.Optional;

public class MainWindowController {
    @FXML
    private TableView<Contact> contacts;

    public void initialize() {
//        contacts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        contacts.setItems(ContactData.getInstance().getContacts());
        contacts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    protected void handleNewContact() {
        FXMLLoader fxmlLoader = getFxmlLoader();
        Dialog<ButtonType> dialog = initializeDialog("Add New Contact", "Use this dialog to add new contact", fxmlLoader);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.OK)) {
            ContactFormController controller = fxmlLoader.getController();
            Contact contact = controller.saveNewContact();
            contacts.refresh();
            contacts.getSelectionModel().select(contact);
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
            controller.updateContact(selectedContact);
            contacts.refresh();
            contacts.getSelectionModel().select(selectedContact);
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
            return new FXMLLoader(Path.of("fxcontacts/src/main/resources/ayo/fxcontacts/contact-form.fxml").toUri().toURL());
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
            ContactData.getInstance().deleteContact(selectedContact);
        }
    }

    @FXML
    protected void handleExit() {
        Platform.exit();
    }
}