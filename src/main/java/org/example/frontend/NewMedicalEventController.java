package org.example.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class NewMedicalEventController {


    @FXML
    private TextField animalIdField;

    @FXML
    private TextField typeField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField doctorField;

    @FXML
    public void handleCancel() {

        Stage stage = (Stage) animalIdField.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleSave() {
        String id = animalIdField.getText();
        String tipus = typeField.getText();
        String orvos = doctorField.getText();


        LocalDate datum = datePicker.getValue();
        String datumString = (datum != null) ? datum.toString() : "Nincs dátum";

        System.out.println("ÚJ ESEMÉNY MENTÉSE: ID=" + id + ", Típus=" + tipus + ", Dátum=" + datumString + ", Orvos=" + orvos);

        handleCancel();
    }
}