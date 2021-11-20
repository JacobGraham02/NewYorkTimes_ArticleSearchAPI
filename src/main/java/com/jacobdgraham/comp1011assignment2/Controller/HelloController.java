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
import java.net.URL;
import java.util.ResourceBundle;

import static com.jacobdgraham.comp1011assignment2.Utilities.APIUtility.*;

public class HelloController implements Initializable {

    String temp[] = new String[2];
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        writeDataToArticlesJson(fetchAPIResultsInString());
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        temp = getCredentialsFromJsonInArray("apiKey_secretKey.json");
    }
}