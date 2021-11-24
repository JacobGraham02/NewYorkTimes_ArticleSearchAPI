package com.jacobdgraham.comp1011assignment2.Controller;

import com.jacobdgraham.comp1011assignment2.Model.Article;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.*;

import static com.jacobdgraham.comp1011assignment2.SceneChanger.changeScene;

public class DetailedArticleViewController implements Initializable {

    @FXML
    private Button btnBackToArticleSearch;

    @FXML
    private Label lblArticleTitle;

    @FXML
    private Label lblLinkToArticle;

    @FXML
    private Label lblArticleHeading;

    @FXML
    private Label lblArticleLeadingParagraph;

    @FXML
    private Label lblSourceOfArticle;

    @FXML
    private TextArea txtAreaArticleTitle;

    @FXML
    private TextArea txtAreaLinkToArticle;

    @FXML
    private TextArea txtAreaArticleHeading;

    @FXML
    private TextArea txtAreaArticleLeadingParagraph;

    @FXML
    private TextArea txtAreaArticleSource;

    @FXML
    void backToArticleSearch(ActionEvent event) throws IOException {
        changeScene(event, "Views/ArticleView.fxml", "Hello!");
        executorService.shutdown();
    }

    private Article articleFromPreviousPage;

    private ExecutorService executorService;

    public void initializeArticleData(Article nytArticle) {
        articleFromPreviousPage = nytArticle;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        executorService = Executors.newFixedThreadPool(1);

        Runnable runnableForArticleObject = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
                populateStuff(articleFromPreviousPage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        executorService.submit(runnableForArticleObject);
    }

    private void populateStuff(Article art) {
        txtAreaArticleTitle.setText(art.getArticleTitle());
        txtAreaArticleHeading.setText(art.getArticleTitleSnippet());
        txtAreaArticleLeadingParagraph.setText(art.getArticleLeadParagraph());
        txtAreaLinkToArticle.setText(art.getUrl());
        txtAreaArticleSource.setText(art.getArticleSource());
    }
}
