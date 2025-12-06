package org.example.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.backend.model.Owner;

public class NewOwnerController {

    private Owner editingOwner;

    @Setter
    private BackendManager backend;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    private Runnable refreshCallBack;

    public void  setRefreshCallback(Runnable refreshCallBack) {
        this.refreshCallBack = refreshCallBack;
    }

    @FXML
    public void handleCancel() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    public void setEditingOwner(Owner owner) {
        this.editingOwner = owner;

        nameField.setText(owner.getName());
        phoneField.setText(owner.getPhone());
        emailField.setText(owner.getEmail());
    }

    @FXML
    public void handleSave() {

        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        if (editingOwner == null) {
            backend.saveOwner(name, phone, email);
            System.out.println("ÚJ TULAJDONOS MENTÉSE: " + name + ", " + phone + ", " + email);
        } else {
            editingOwner.setName(name);
            editingOwner.setPhone(phone);
            editingOwner.setEmail(email);

            backend.updateOwner(editingOwner);
            System.out.println("TULAJDONOS MÓDOSÍTVA: " + name + ", " + phone + ", " + email);
        }

        if (refreshCallBack != null) refreshCallBack.run();

        handleCancel();
    }

}