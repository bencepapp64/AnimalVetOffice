package org.example;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableContentController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Tulaj> tableView;

    @FXML
    private TableColumn<Tulaj, String> nameColumn;

    @FXML
    private TableColumn<Tulaj, String> phoneColumn;

    @FXML
    private TableColumn<Tulaj, String> emailColumn;

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
        // TODO BACKEND: Új felvétel ablak megnyitása
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