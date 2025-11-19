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
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nev"));
        breedColumn.setCellValueFactory(new PropertyValueFactory<>("fajta"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("kor"));

        tableView.setItems(FXCollections.observableArrayList());

        // TODO: Itt hívhatja majd a backend az adatbázis lekérdezést
    }

    @FXML
    public void handleNewClick() {
        System.out.println("Új állat hozzáadása...");
    }
}