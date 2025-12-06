package org.example.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;


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

    }

    private void openWindow(String fxml, String title) {
    try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/example/" + fxml));
        Parent root = loader.load();
        Object controller = loader.getController();
        if (controller instanceof TableContentController){
            ((TableContentController)controller).setBackend(backend);
            ((TableContentController)controller).fillTable(); //Ezt nem szabad nezni

        }

        if (controller instanceof AnimalsController) {
            ((AnimalsController) controller).setBackend(backend);
            ((AnimalsController) controller).fillTable();
        }

        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
