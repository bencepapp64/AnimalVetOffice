package org.example.frontend;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.Setter;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StatisticsController {

    @Setter
    private BackendManager backend;

    @FXML
    private Label numberOwners;
    @FXML
    private Label numberAnimals;
    @FXML
    private Label numberEvents;


    public void fillNumbers(){
        List<Integer> num = backend.getNumbersOfOwnersAnimalsEvents();
        numberOwners.setText(num.get(0).toString());
        numberAnimals.setText(num.get(1).toString());
        numberEvents.setText(num.get(2).toString());
    }


}
