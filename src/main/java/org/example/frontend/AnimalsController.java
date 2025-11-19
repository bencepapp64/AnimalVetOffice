package org.example.frontend;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.backend.Allat;

public class AnimalsController {

    @FXML
    private TableView<Allat> tableView;

    @FXML
    private TableColumn<Allat, String> idColumn;

    @FXML
    private TableColumn<Allat, String> breedColumn;

    @FXML
    private TableColumn<Allat, String> ageColumn;

    @FXML
    private TableColumn<Allat, String> diagnoseColumn;


    @FXML
    private TableColumn<Allat, String> nameColumn;

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