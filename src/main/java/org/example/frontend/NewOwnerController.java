package org.example.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;

public class NewOwnerController {

    @Setter
    private BackendManager backend;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;


    @FXML
    public void handleCancel() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleSave() {

        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        System.out.println("ÚJ TULAJDONOS MENTÉSE: " + name + ", " + phone + ", " + email);
        backend.saveOwner(name, phone, email);

        handleCancel();
    }
}