package org.example.frontend;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.backend.model.MedicalEvent;

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

            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("new_medical_event.fxml"));
            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Új orvosi esemény");
            stage.setScene(new javafx.scene.Scene(root));


            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);

            stage.showAndWait();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    }