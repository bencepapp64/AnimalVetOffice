package org.example.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.backend.SpringBackendManager;

import java.io.IOException;


// Szemán itt alkotott először
public class MainController {

    @Setter
    private BackendManager backend ;

    public BorderPane mainBorderPane;


    @FXML
    private void initialize() {

    }

    public void handleAnimalsClick(ActionEvent actionEvent) {
        openWindow("animals.fxml", "Állatok");
    }

    public void handleOwnersClick(ActionEvent actionEvent) {
        openWindow("table_content.fxml", "Tulajdonosok");
    }

    public void handleMedicalActionsClick(ActionEvent actionEvent) {
        openWindow("medical.fxml", "Orvosi események");
    }

    public void handleStatisticsClick(ActionEvent actionEvent) {
        backend.test();
    }

    private void openWindow(String fxml, String title) {
    try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/example/" + fxml));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
