package com.jacobdgraham.comp1011assignment2;

import com.jacobdgraham.comp1011assignment2.Controller.ArticleViewController;
import com.jacobdgraham.comp1011assignment2.Controller.DetailedArticleViewController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class ApplicationLauncher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationLauncher.class.getResource("Views/ArticleView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icon.png")));
        ArticleViewController controller = fxmlLoader.getController();
        controller.setHostServices(getHostServices());
        stage.setTitle("New York Times article search");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}