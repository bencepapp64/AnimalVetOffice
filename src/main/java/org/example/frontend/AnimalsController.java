package org.example.frontend;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.backend.model.Animal;

public class AnimalsController {

    @FXML
    private TableView<Animal> tableView;

    @FXML
    private TableColumn<Animal, String> idColumn;

    @FXML
    private TableColumn<Animal, String> breedColumn;

    @FXML
    private TableColumn<Animal, String> ageColumn;

    @FXML
    private TableColumn<Animal, String> diagnoseColumn;


    @FXML
    private TableColumn<Animal, String> nameColumn;

    @FXML
    public void initialize() {


        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        diagnoseColumn.setCellValueFactory(new PropertyValueFactory<>("diagnozis"));
        breedColumn.setCellValueFactory(new PropertyValueFactory<>("fajta"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("kor"));

        tableView.setItems(FXCollections.observableArrayList());

        // TODO: Itt hívhatja majd a backend az adatbázis lekérdezést
    }

    @FXML
    public void handleNewClick() {
        try {
            // Itt adjuk meg a "new_animal.fxml" nevét!
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("new_animal.fxml"));
            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Új állat");
            stage.setScene(new javafx.scene.Scene(root));
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    }