package org.example.frontend;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.backend.model.Owner;

import java.io.IOException;

public class TableContentController {

    @Setter
    private BackendManager backend;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Owner> tableView;

    @FXML
    private TableColumn<Owner, String> nameColumn;

    @FXML
    private TableColumn<Owner, String> phoneColumn;

    @FXML
    private TableColumn<Owner, String> emailColumn;

    @FXML
    public void initialize() {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefonszam"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));


        tableView.setItems(FXCollections.observableArrayList());

        // TODO BACKEND: Itt kell majd meghívni az adatbázis lekérdezést és feltölteni a listát

    }

    @FXML
    public void handleNewClick() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/org/example/new_owner.fxml"));
            Parent root = loader.load();

            ((NewOwnerController)loader.getController()).setBackend(backend);

            Stage stage = new Stage();
            stage.setTitle("Új tulajdonos");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleEditClick() {
        // TODO BACKEND: Szerkesztés logika
    }

    @FXML
    public void handleDeleteClick() {
        // TODO BACKEND: Törlés logika
    }
}