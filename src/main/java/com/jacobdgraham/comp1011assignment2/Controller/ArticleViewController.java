package com.jacobdgraham.comp1011assignment2.Controller;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.jacobdgraham.comp1011assignment2.Model.Credentials;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ResourceBundle;

import static com.jacobdgraham.comp1011assignment2.Utilities.APIUtility.*;

public class ArticleViewController implements Initializable {

    String[] stringArrayArticleSearchCredentials = new String[2];
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException, InterruptedException {
//        fetchApiResultsInJsonFile(fetchAPIConnection());
//        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stringArrayArticleSearchCredentials = getCredentialsFromJsonInArray("apiKey_secretKey.json");


        try {
            System.out.println(Arrays.toString(getArticlesFromJson().getDocs()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}