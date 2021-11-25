package com.jacobdgraham.comp1011assignment2.Controller;

import com.jacobdgraham.comp1011assignment2.Model.Article;
import com.jacobdgraham.comp1011assignment2.Model.ArticleImage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.jacobdgraham.comp1011assignment2.SceneChanger.changeScene;
import static com.jacobdgraham.comp1011assignment2.Utilities.APIUtility.*;

public class DetailedArticleViewController implements Initializable {

    @FXML
    private ImageView imgViewArticlePicture;

    @FXML
    private Button btnBackToArticleSearch;

    @FXML
    private Label lblArticleTitle;

    @FXML
    private Hyperlink linkArticleOnline;

    @FXML
    private Label lblArticleHeadingSnippet;

    @FXML
    private Label lblArticleLeadParagraph;

    @FXML
    private Label lblArticleSource;

    final String newYorkTimesImagePrepend = "https://www.nytimes.com/";

    @FXML
    void backToArticleSearch(final ActionEvent event) throws IOException {
        changeScene(event, "Views/ArticleView.fxml", "Hello!");
    }

    private Article articleFromPreviousPage;

    final public void initializeArticleData(final Article nytArticle) {
        articleFromPreviousPage = nytArticle;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        final KeyFrame kf1 = new KeyFrame(Duration.millis(240), e -> populateImageView());
        final KeyFrame kf2 = new KeyFrame(Duration.millis(250), e ->
        {
            lblArticleTitle.setText(articleFromPreviousPage.getArticleTitle());
            lblArticleHeadingSnippet.setText(articleFromPreviousPage.getArticleTitleSnippet());
            lblArticleLeadParagraph.setText(articleFromPreviousPage.getArticleLeadParagraph());
            linkArticleOnline.setText(articleFromPreviousPage.getUrl());
        });

        final Timeline timeline = new Timeline(kf1, kf2);
        Platform.runLater(timeline::play);
    }
    private void populateImageView() {
        final ArticleImage imageLocation = articleFromPreviousPage.getMultimedia()[0];
        final String articleImageUrl = newYorkTimesImagePrepend+imageLocation.getArticleImageUrl();

        double imageWidth = articleFromPreviousPage.getMultimedia()[0].getWidth();
        double imageHeight = articleFromPreviousPage.getMultimedia()[0].getHeight();

        imgViewArticlePicture.setFitWidth(imageWidth);
        imgViewArticlePicture.setFitHeight(imageHeight);
        imgViewArticlePicture.setImage(new Image(articleImageUrl));
    }
}
