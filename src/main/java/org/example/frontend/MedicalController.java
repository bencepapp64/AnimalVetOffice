package org.example.frontend;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.backend.OrvosiEsemeny;

public class MedicalController {

    @FXML
    private TableView<OrvosiEsemeny> tableView;

    @FXML
    private TableColumn<OrvosiEsemeny, String> idColumn;

    @FXML
    private TableColumn<OrvosiEsemeny, String> typeColumn;

    @FXML
    private TableColumn<OrvosiEsemeny, String> dateColumn;

    @FXML
    private TableColumn<OrvosiEsemeny, String> nameColumn;

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
        System.out.println("Új orvosi esemény...");
    }
}