package org.example.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewAnimalController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField speciesField;

    @FXML
    private TextField breedField;
    @FXML
    private TextField ageField;

    @FXML
    public void handleCancel() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleSave() {
        String nev = nameField.getText();
        String faj = speciesField.getText();
        String fajta = breedField.getText();
        String kor = ageField.getText();

        System.out.println("ÚJ ÁLLAT MENTÉSE: " + nev + " (" + faj + ", " + fajta + "), " + kor + " éves");

        handleCancel();
    }
}