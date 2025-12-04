package org.example.frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.backend.model.Owner;

import java.io.IOException;
import java.util.List;

public class TableContentController {

    @Setter
    private BackendManager backend;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Owner> tableView;

    @FXML
    private TableColumn<Owner, String> nameColumn;

    @FXML
    private TableColumn<Owner, String> phoneColumn;

    @FXML
    private TableColumn<Owner, String> emailColumn;

    @FXML
    public void initialize() {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.setItems(FXCollections.observableArrayList());
    }

    public void fillTable(){
        List<Owner> owners = backend.getOwners();
        ObservableList<Owner> data = FXCollections.observableArrayList(owners);
        tableView.setItems(data);
    }

    @FXML
    public void handleNewClick() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/org/example/new_owner.fxml"));
            Parent root = loader.load();

            ((NewOwnerController)loader.getController()).setBackend(backend);
            ((NewOwnerController)loader.getController()).setRefreshCallback(this::fillTable);
            Stage stage = new Stage();
            stage.setTitle("Új tulajdonos");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
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