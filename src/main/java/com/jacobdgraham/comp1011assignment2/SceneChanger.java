package com.jacobdgraham.comp1011assignment2;

import com.jacobdgraham.comp1011assignment2.Controller.DetailedArticleViewController;
import com.jacobdgraham.comp1011assignment2.Model.Article;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {

    public static void changeScene(ActionEvent actionEvent, String newFxmlViewName, String newSceneTitle) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(new Object() {
            // The same process is applied here as in the initial application start 'HelloApplication'. A new object containing the FXML file is created and
            // loaded using the FMXLLoader class.
        }.getClass().getResource(newFxmlViewName));
        Parent rootLocation = fxmlLoader.load();
        Scene scene = new Scene(rootLocation);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(newSceneTitle);
        stage.show();
    }

    public static void changeScene(ActionEvent actionEvent, String newFxmlViewName, String sceneTitle, Article articleFromNyt, HostServices hostService) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(new Object() {

        }.getClass().getResource(newFxmlViewName));
        Parent rootLocation = fxmlLoader.load();
        Scene scene = new Scene(rootLocation);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        DetailedArticleViewController detailedArticleViewController = fxmlLoader.getController();
        detailedArticleViewController.initializeArticleData(articleFromNyt);
        detailedArticleViewController.setHostServices(hostService);

        stage.setTitle(sceneTitle);
        stage.setScene(scene);
        stage.show();
    }
}
