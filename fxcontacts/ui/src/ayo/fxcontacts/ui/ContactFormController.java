package ayo.fxcontacts.ui;

import ayo.fxcontacts.common.Contact;
import ayo.fxcontacts.xml.ContactData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ContactFormController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField notesField;

    Contact saveNewContact() {
        Contact contact = new Contact();
        setContactFields(contact);
        ContactData.getInstance().addContact(contact);
        return contact;
    }

    void updateContact(Contact contact) {
        setContactFields(contact);
        ContactData.getInstance().saveContacts();
    }

    void populateForm(Contact contact) {
        firstNameField.setText(contact.getFirstName());
        lastNameField.setText(contact.getLastName());
        phoneNumberField.setText(contact.getPhoneNumber());
        notesField.setText(contact.getNotes());
    }

    private void setContactFields(Contact contact) {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        String notes = notesField.getText().trim();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhoneNumber(phoneNumber);
        contact.setNotes(notes);
    }

}
