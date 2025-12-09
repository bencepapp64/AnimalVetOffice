package org.example.frontend;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.backend.model.Animal;
import org.example.backend.model.MedicalEvent;

import java.time.LocalDate;
import java.util.List;

public class NewMedicalEventController {

    @Setter
    private BackendManager backend;
    private MedicalEvent editingMedicalEvent;

    @FXML
    private ComboBox<Animal> animalComboBox;

    @FXML
    private TextField typeField;

    @FXML
    private TextField doctorField;

    @FXML
    private DatePicker datePicker;

    private Runnable refreshCallBack;

    public void setRefreshCallback(Runnable refreshCallBack) {
        this.refreshCallBack = refreshCallBack;
    }

    public void loadAnimals() {


        List<Animal> animals = backend.getAnimals();

        animalComboBox.setItems(FXCollections.observableArrayList(animals));

        animalComboBox.setCellFactory(cb -> new ListCell<>() {
            @Override
            protected void updateItem(Animal item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getName());
            }
        });

        animalComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Animal item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getName());
            }
        });
    }

    public void setEditingMedicalEvent(MedicalEvent event) {
        this.editingMedicalEvent = event;

        if (event != null) {
            typeField.setText(event.getType());
            doctorField.setText(event.getName());
            datePicker.setValue(event.getDate());

            if (backend != null && event.getAnimal() != null) {
                Animal currentAnimal = event.getAnimal();
                animalComboBox.getSelectionModel().select(currentAnimal);
            }
        }
    }

    @FXML
    public void handleSave() {
        Animal selectedAnimal = animalComboBox.getValue();

        if (selectedAnimal == null) {
            new Alert(Alert.AlertType.WARNING, "Kérlek válassz ki egy állatot!").showAndWait();
            return;
        }

        Integer animalId = selectedAnimal.getId().intValue();
        String name = doctorField.getText();
        String type = typeField.getText();
        LocalDate date = datePicker.getValue();

        if (editingMedicalEvent == null) {
            backend.saveMedicalEvent(animalId, type, date, name);
            System.out.println("ÚJ ESEMÉNY MENTÉSE");
        } else {
            editingMedicalEvent.setName(name);
            editingMedicalEvent.setType(type);
            editingMedicalEvent.setAnimal(selectedAnimal);
            editingMedicalEvent.setDate(date);

            backend.updateMedicalEvent(editingMedicalEvent);
            System.out.println("ESEMÉNY MÓDOSÍTVA");
        }

        if (refreshCallBack != null) refreshCallBack.run();
        handleCancel();
    }


    @FXML
    public void handleCancel() {
        Stage stage = (Stage) typeField.getScene().getWindow();
        stage.close();
    }
}