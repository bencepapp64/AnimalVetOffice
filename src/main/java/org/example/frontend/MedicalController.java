package org.example.frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.backend.model.Animal;
import org.example.backend.model.MedicalEvent;

import java.io.IOException;
import java.util.List;

public class MedicalController {

    @Setter
    private BackendManager backend;

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
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableView.setItems(FXCollections.observableArrayList());

        // TODO: Backend adatbázis lekérdezés helye
    }

    public void fillTable(){
        List<MedicalEvent> events = backend.getMedicalEvents();
        ObservableList<MedicalEvent> data = FXCollections.observableArrayList(events);
        tableView.getItems().setAll(data);
    }

    @FXML
    public void handleNewClick() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/org/example/new_medical_event.fxml"));
            Parent root = loader.load();
            NewMedicalEventController controller = loader.getController();

            controller.setBackend(backend);
            controller.setRefreshCallback(this::fillTable);
            controller.loadAnimals();

            Stage stage = new Stage();
            stage.setTitle("Új esemény");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDeleteClick() {
        MedicalEvent selectedMedicalEvent = tableView.getSelectionModel().getSelectedItem();
        if (selectedMedicalEvent != null) {
            backend.deleteMedicalEventById(selectedMedicalEvent.getId());
            System.out.println("Deleted MedicalId: " + selectedMedicalEvent.getId());
            fillTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Kérlek válassz ki egy sort a törléshez!").showAndWait();
        }
    }

    @FXML
    public void handleEditClick() {
        MedicalEvent selectedMedicalEvent = tableView.getSelectionModel().getSelectedItem();

        if (selectedMedicalEvent == null) {
            new Alert(Alert.AlertType.WARNING, "Kérlek válassz ki egy sort a szerkesztéshez!").showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/new_medical_event.fxml"));
            Parent root = loader.load();

            NewMedicalEventController controller = loader.getController();
            controller.setBackend(backend);
            controller.setRefreshCallback(this::fillTable);
            controller.loadAnimals();
            controller.setEditingMedicalEvent(selectedMedicalEvent);

            Stage stage = new Stage();
            stage.setTitle("Esemény szerkesztése");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}