package org.example.frontend;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.backend.SpringBackendManager;

public class App extends Application {


    private BackendManager backend;

    @Override
    public void start(Stage stage) throws IOException {
        backend = new SpringBackendManager();
        backend.start();

        var loader = new FXMLLoader(getClass().getResource("/org/example/main.fxml"));
        var scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/org/example/style.css").toExternalForm()); // Karcsi elso komment


        ((MainController)loader.getController()).setBackend(backend);

        stage.setScene(scene);
        stage.setTitle("AnimalVetOffice");  //Teszt
        stage.show();


    }

}
