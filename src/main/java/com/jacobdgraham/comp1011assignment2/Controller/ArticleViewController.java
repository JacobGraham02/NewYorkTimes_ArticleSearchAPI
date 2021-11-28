package com.jacobdgraham.comp1011assignment2.Controller;

import com.jacobdgraham.comp1011assignment2.Model.Article;
import com.jacobdgraham.comp1011assignment2.SceneChanger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.HostServices;
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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.jacobdgraham.comp1011assignment2.SceneChanger.changeScene;
import static com.jacobdgraham.comp1011assignment2.Utilities.APIUtility.*;

public class ArticleViewController implements Initializable {
    private Article currentlySelectedArticle;

    private TreeSet<Article> treeSetNewYorkTimesArticles;

    @FXML
    private Label lblHeading;

    @FXML
    private Label lblSubHeading;

    @FXML
    private Label lblInformationAboutArticlesFetched;

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

    private String txtfieldData;

    private HostServices hostServices;

    public void setHostServices(HostServices hostServicesParam) {
        this.hostServices = hostServicesParam;
    }

    @FXML
    void btnTableRowClicked(ActionEvent event) throws IOException {
        changeScene(event, "Views/DetailedArticleView.fxml", currentlySelectedArticle.getArticleTitle(), currentlySelectedArticle, hostServices);
    }

    private boolean validateTextFieldData() {
        return txtKeywords.getText().trim().replaceAll(" ", "%20").matches("[a-zA-Z0-9%]{2,50}");
    }

    private Alert generateMessage(final String headerText, final String contentText, final Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }

    @FXML
    void searchForArticles(ActionEvent event) {
        if (!validateTextFieldData()) {
            generateMessage("Input error", "Please enter some text between 2 and 50 characters long", Alert.AlertType.ERROR).showAndWait();
            return;
        }
        lblInformationAboutArticlesFetched.setText("Fetching articles...");

        tblViewArticleTitles.getItems().clear();
        treeSetNewYorkTimesArticles.clear();
        txtKeywords.clear();

        txtfieldData = txtKeywords.getText().trim();

        final KeyFrame keyFrameFetchArticleInfo = new KeyFrame(Duration.millis(250), e ->
        {
            try {
                fetchApiResultsInJsonFile(fetchAPIConnectionWithSpecificKeyword(txtfieldData));
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
            try {
                treeSetNewYorkTimesArticles.addAll(Arrays.asList(getArticlesFromJson().getDocs()));
                tblViewArticleTitles.getItems().addAll(treeSetNewYorkTimesArticles);
                lblInformationAboutArticlesFetched.setText("You have fetched " + treeSetNewYorkTimesArticles.size() + " articles from the New York Times website");
            } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
                lblInformationAboutArticlesFetched.setText("There were no fetched articles on the New York Times website containing the specified keywords");
            }
            tblViewArticleTitles.getSelectionModel().clearSelection();
        });

        final Timeline timeline = new Timeline(keyFrameFetchArticleInfo);
        Platform.runLater(timeline::play);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] stringArrayArticleSearchCredentials = new String[2];
        treeSetNewYorkTimesArticles = new TreeSet<>();
        stringArrayArticleSearchCredentials = getCredentialsFromJsonInArray();

        tblViewColumnArticleTitle.setCellValueFactory(new PropertyValueFactory<>("articleTitle"));


        tblViewArticleTitles.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {

            tblViewArticleTitles.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {

                    currentlySelectedArticle = tblViewArticleTitles.getSelectionModel().getSelectedItem();

                    btnClickedTableRow.fireEvent(new ActionEvent());
                }
            });
        });
    }
}

