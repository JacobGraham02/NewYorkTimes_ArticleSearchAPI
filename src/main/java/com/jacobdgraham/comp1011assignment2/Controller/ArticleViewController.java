package com.jacobdgraham.comp1011assignment2.Controller;

import com.jacobdgraham.comp1011assignment2.Model.Article;
import com.jacobdgraham.comp1011assignment2.SceneChanger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.TreeSet;

import static com.jacobdgraham.comp1011assignment2.SceneChanger.changeScene;
import static com.jacobdgraham.comp1011assignment2.Utilities.APIUtility.*;

public class ArticleViewController implements Initializable {
    private static Article currentlySelectedArticle;

    private TreeSet<Article> treeSetNewYorkTimesArticles;
    private int tableViewMaxElementCount;

    @FXML
    private Label lblHeading;

    @FXML
    private Label lblSubHeading;

    @FXML
    private TextField txtKeywords;

    @FXML
    private Button btnSearchForArticles;

    @FXML
    private Button btnClickedTableRow;

    @FXML
    private TableView<Article> tblViewArticleTitles;

    @FXML
    private TableColumn<Article, String> tblViewColumnArticleTitle;

    @FXML
    void btnTableRowClicked(ActionEvent event) throws IOException {
        changeScene(event, "Views/DetailedArticleView.fxml", "Test.fxml");
        System.out.println("button was clicked");
    }

    private boolean validateTextFieldData() {
        return txtKeywords.getText().trim().replaceAll(" ", "%20").matches("[a-zA-Z]{2,50}");
    }

    private Alert generateMessage(String headerText, String contentText, Alert.AlertType alertType) {
        Alert errorAlert = new Alert(alertType);
        errorAlert.setHeaderText(headerText);
        errorAlert.setContentText(contentText);
        return errorAlert;
    }

    @FXML
    void searchForArticles(ActionEvent event) throws IOException, InterruptedException {
        if (!validateTextFieldData()) {
            // generateMessage("Input error", "Please enter some text between 1 and 50 characters long", Alert.AlertType.ERROR);
            runThreadForMessage("Input error", "Please enter some text between 1 and 50 characters long", Alert.AlertType.ERROR);
            return;
        }
        tblViewArticleTitles.getItems().clear();
        treeSetNewYorkTimesArticles.clear();

        fetchApiResultsInJsonFile(fetchAPIConnectionWithSpecificKeyword(txtKeywords.getText().trim()));

        treeSetNewYorkTimesArticles.addAll(Arrays.asList(getArticlesFromJson().getDocs()));
        txtKeywords.clear();

        runThreadForMessage("Number of messages fetched", "You have fetched " + treeSetNewYorkTimesArticles.size() +
                " articles from the New York Times website", Alert.AlertType.INFORMATION);

        tblViewArticleTitles.getItems().addAll(treeSetNewYorkTimesArticles);
        tableViewMaxElementCount = tblViewArticleTitles.getItems().size();
    }

    private void runThreadForMessage(String headerText, String contentText, Alert.AlertType alertType) {
        new Thread(() -> Platform.runLater(() -> {
            generateMessage(headerText, contentText, alertType).showAndWait();
        })).start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] stringArrayArticleSearchCredentials = new String[2];
        treeSetNewYorkTimesArticles = new TreeSet<>();
        stringArrayArticleSearchCredentials = getCredentialsFromJsonInArray();

        tblViewColumnArticleTitle.setCellValueFactory(new PropertyValueFactory<>("articleTitle"));

        tblViewArticleTitles.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {

            System.out.println(treeSetNewYorkTimesArticles.toArray()[tblViewArticleTitles.getSelectionModel().getFocusedIndex()]);
            tblViewArticleTitles.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    Article articleData = tblViewArticleTitles.getSelectionModel().getSelectedItem();
                    btnClickedTableRow.fireEvent(new ActionEvent());
                }
            });
        });
    }
}
