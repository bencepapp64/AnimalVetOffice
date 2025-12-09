package org.example.frontend;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.Setter;
import org.example.backend.model.Animal;
import org.example.backend.model.Owner;

import java.util.List;

public class NewAnimalController {

    private Animal editingAnimal;

    @Setter
    private BackendManager backend;

    @FXML
    private TextField nameField;

    @FXML
    private TextField diagnoseField;

    @FXML
    private TextField breedField;
    @FXML
    private TextField ageField;
    @FXML
    private ComboBox<Owner> ownerComboBox;

    private Runnable refreshCallBack;

    public void  setRefreshCallback(Runnable refreshCallBack) {
        this.refreshCallBack = refreshCallBack;
    }

    public void loadOwners() {
        //if (backend == null) return;

        List<Owner> owners = backend.getOwners();

        ownerComboBox.setItems(FXCollections.observableArrayList(owners));

        ownerComboBox.setCellFactory(cb -> new ListCell<>() {
            @Override
            protected void updateItem(Owner item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getName());
            }
        });

        ownerComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Owner item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getName());
            }
        });

        ownerComboBox.setConverter(new StringConverter<Owner>() {
            @Override
            public String toString(Owner object) {
                return (object == null) ? null : object.getName(); // <-- CSAK A NEVET ADJA VISSZA
            }

            @Override
            public Owner fromString(String string) {
                return null; // Nem szükséges
            }
        });
    }

    @FXML
    public void handleCancel() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    public void setEditingAnimal(Animal animal) {
        this.editingAnimal = animal;

        nameField.setText(animal.getName());
        diagnoseField.setText(animal.getDiagnose());
        breedField.setText(animal.getBreed());
        ageField.setText(String.valueOf(animal.getAge()));

        if (animal.getOwner() != null) {
            ownerComboBox.getSelectionModel().select(animal.getOwner());
        }
    }

    @FXML
    public void handleSave() {
        Owner selectedOwner = ownerComboBox.getValue();

        String name = nameField.getText();
        String diagnose = diagnoseField.getText();
        String breed = breedField.getText();
        String age = ageField.getText();



        if (editingAnimal == null) {
            backend.saveAnimal(name, breed, Integer.parseInt(age), diagnose, selectedOwner.getId().intValue());
            System.out.println("ÚJ ÁLLAT MENTÉSE: " + name + " (" + breed + "), " + age + " éves, diagnózis: " + diagnose + " Tulajdonos ID: " + selectedOwner.getId().intValue());
        } else {
            editingAnimal.setName(name);
            editingAnimal.setBreed(breed);
            editingAnimal.setAge(Integer.parseInt(age));
            editingAnimal.setDiagnose(diagnose);

            backend.updateAnimal(editingAnimal, selectedOwner.getId().intValue());
            System.out.println("ÁLLAT MÓDOSÍTVA: " + name + " (" + breed + "), " + age + " éves, diagnózis: " + diagnose);
        }

        if (refreshCallBack != null) refreshCallBack.run();

        handleCancel();
    }
}