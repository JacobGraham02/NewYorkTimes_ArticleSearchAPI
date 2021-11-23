package com.jacobdgraham.comp1011assignment2.Controller;

import com.jacobdgraham.comp1011assignment2.Model.Article;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.TreeSet;

import static com.jacobdgraham.comp1011assignment2.Utilities.APIUtility.*;

public class ArticleViewController implements Initializable {

    private TreeSet<Article> treeMapNewYorkTimesArticles;

    @FXML
    private Label lblHeading;

    @FXML
    private Label lblSubHeading;

    @FXML
    private TextField txtKeywords;

    @FXML
    private Button btnSearchForArticles;

    @FXML
    private TableView<Article> tblViewArticleTitles;

    @FXML
    private TableColumn<Article, String> tblViewColumnArticleTitle;

    @FXML
    private TableColumn<Article, String> tblViewColumnArticleSource;

    private boolean validateTextFieldData() {
        return txtKeywords.getText().trim().replaceAll(" ", "%20").matches("[a-zA-Z]{2,50}");
    }

    private void generateMessage(String headerText, String contentText, Alert.AlertType alertType) {
        Alert errorAlert = new Alert(alertType);
        errorAlert.setHeaderText(headerText);
        errorAlert.setContentText(contentText);
        errorAlert.showAndWait();
    }

    @FXML
    void searchForArticles(ActionEvent event) throws IOException, InterruptedException {
        if (!validateTextFieldData()) {
            generateMessage("Input error", "Please enter some text between 1 and 50 characters long", Alert.AlertType.ERROR);
            return;
        }
        tblViewArticleTitles.getItems().clear();
        treeMapNewYorkTimesArticles.clear();
        fetchApiResultsInJsonFile(fetchAPIConnectionWithSpecificKeyword(txtKeywords.getText().trim()));
        treeMapNewYorkTimesArticles.addAll(Arrays.asList(getArticlesFromJson().getDocs()));
        txtKeywords.clear();

        generateMessage("Number of messages generated",
                "You have fetched " + treeMapNewYorkTimesArticles.size() + " articles from the New York Times website",
                Alert.AlertType.INFORMATION);
        tblViewArticleTitles.getItems().addAll(treeMapNewYorkTimesArticles);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] stringArrayArticleSearchCredentials = new String[2];
        treeMapNewYorkTimesArticles = new TreeSet<>();
        stringArrayArticleSearchCredentials = getCredentialsFromJsonInArray();

        tblViewColumnArticleTitle.setCellValueFactory(new PropertyValueFactory<>("articleTitle"));
    }
}
