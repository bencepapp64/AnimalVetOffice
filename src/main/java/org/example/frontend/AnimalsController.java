package org.example.frontend;

import javafx.beans.property.SimpleStringProperty;
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
import org.example.backend.model.Owner;

import java.io.IOException;
import java.util.List;

public class AnimalsController {

    @Setter
    private BackendManager backend;

    @FXML
    private TableView<Animal> tableView;

    @FXML
    private TableColumn<Animal, String> nameColumn;

    @FXML
    private TableColumn<Animal, String> breedColumn;

    @FXML
    private TableColumn<Animal, Integer> ageColumn;

    @FXML
    private TableColumn<Animal, String> diagnoseColumn;

    @FXML
    private TableColumn<Animal, String> ownerColumn;

    @FXML
    public void initialize() {


        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        diagnoseColumn.setCellValueFactory(new PropertyValueFactory<>("diagnose"));
        breedColumn.setCellValueFactory(new PropertyValueFactory<>("breed"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
//        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("Owner.name"));

        ownerColumn.setCellValueFactory(cellData -> {
            Animal animal = cellData.getValue();
            if (animal != null && animal.getOwner() != null) {
                return new SimpleStringProperty(animal.getOwner().getName());
            } else {
                return new SimpleStringProperty("Nincs tulajdonos");
            }
        });

        tableView.setItems(FXCollections.observableArrayList());

        // TODO: Itt hívhatja majd a backend az adatbázis lekérdezést
    }

    public void fillTable(){
        List<Animal> animals = backend.getAnimals();
        ObservableList<Animal> data = FXCollections.observableArrayList(animals);
        tableView.getItems().setAll(data);
    }

    @FXML
    public void handleNewClick() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/org/example/new_animal.fxml"));
            Parent root = loader.load();

            NewAnimalController controller = loader.getController();
            controller.setBackend(backend);
            controller.setRefreshCallback(this::fillTable);
            controller.loadOwners();

            Stage stage = new Stage();
            stage.setTitle("Új állat");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDeleteClick() {
        Animal selectedAnimal = tableView.getSelectionModel().getSelectedItem();
        if (selectedAnimal != null) {
            backend.deleteAnimalById(selectedAnimal.getId());
            System.out.println("Deleted AnimalId: " + selectedAnimal.getId());
            fillTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Kérlek válassz ki egy sort a törléshez!").showAndWait();
        }
    }

    @FXML
    public void handleEditClick() {
        Animal selectedAnimal = tableView.getSelectionModel().getSelectedItem();

        if (selectedAnimal == null) {
            new Alert(Alert.AlertType.WARNING, "Kérlek válassz ki egy sort a szerkesztéshez!").showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/new_animal.fxml"));
            Parent root = loader.load();

            NewAnimalController controller = loader.getController();
            controller.setBackend(backend);
            controller.setRefreshCallback(this::fillTable);
            controller.loadOwners();
            controller.setEditingAnimal(selectedAnimal);

            Stage stage = new Stage();
            stage.setTitle("Állat szerkesztése");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}