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
import org.example.backend.model.Animal;

import java.io.IOException;

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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/org/example/new_animal.fxml"));
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