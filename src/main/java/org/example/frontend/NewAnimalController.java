package org.example.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.backend.model.Animal;
import org.example.backend.model.Owner;

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

    private Runnable refreshCallBack;

    public void  setRefreshCallback(Runnable refreshCallBack) {
        this.refreshCallBack = refreshCallBack;
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
    }

    @FXML
    public void handleSave() {
        String name = nameField.getText();
        String diagnose = diagnoseField.getText();
        String breed = breedField.getText();
        String age = ageField.getText();



        if (editingAnimal == null) {
            backend.saveAnimal(name, breed, Integer.parseInt(age), diagnose);
            System.out.println("ÚJ ÁLLAT MENTÉSE: " + name + " (" + breed + "), " + age + " éves, diagnózis: " + diagnose);
        } else {
            editingAnimal.setName(name);
            editingAnimal.setBreed(breed);
            editingAnimal.setAge(Integer.parseInt(age));
            editingAnimal.setDiagnose(diagnose);

            backend.updateAnimal(editingAnimal);
            System.out.println("ÁLLAT MÓDOSÍTVA: " + name + " (" + breed + "), " + age + " éves, diagnózis: " + diagnose);
        }

        if (refreshCallBack != null) refreshCallBack.run();

        handleCancel();
    }
}