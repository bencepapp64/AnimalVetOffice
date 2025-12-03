package org.example.frontend;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.backend.model.MedicalEvent;

import java.io.IOException;

public class MedicalController {

    @FXML
    private TableView<MedicalEvent> tableView;

    @FXML
    private TableColumn<MedicalEvent, String> idColumn;

    @FXML
    private TableColumn<MedicalEvent, String> typeColumn;

    @FXML
    private TableColumn<MedicalEvent, String> dateColumn;

    @FXML
    private TableColumn<MedicalEvent, String> nameColumn;

    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("tipus"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("orvosNeve"));

        tableView.setItems(FXCollections.observableArrayList());

        // TODO: Backend adatbázis lekérdezés helye
    }

    @FXML
    public void handleNewClick() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/org/example/new_medical_event.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Új tulajdonos");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }