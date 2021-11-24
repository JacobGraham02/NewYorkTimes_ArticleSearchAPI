package com.jacobdgraham.comp1011assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {

    public static void changeScene(ActionEvent actionEvent, String newViewName, String newSceneTitle) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(new Object() {
            // The same process is applied here as in the initial application start 'HelloApplication'. A new object containing the FXML file is created and
            // loaded using the FMXLLoader class.
        }.getClass().getResource(newViewName));
        Parent rootLocation = fxmlLoader.load();
        Scene scene = new Scene(rootLocation);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(newSceneTitle);
        stage.show();
    }
}
