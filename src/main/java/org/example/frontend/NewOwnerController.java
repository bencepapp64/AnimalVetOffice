package org.example.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewOwnerController {

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

        String nev = nameField.getText();
        String telefon = phoneField.getText();
        String email = emailField.getText();

        System.out.println("ÚJ TULAJDONOS MENTÉSE: " + nev + ", " + telefon + ", " + email);


        handleCancel();
    }
}