package com.jacobdgraham.comp1011assignment2.Controller;

import com.jacobdgraham.comp1011assignment2.Model.Article;
import com.jacobdgraham.comp1011assignment2.Model.ArticleImage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
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
    private VBox vboxForArticleImage;

    @FXML
    private Label lblImageViewSuccess;

    private final String newYorkTimesImagePrepend = "https://www.nytimes.com/";

    private HostServices hostServices;

    public void setHostServices(HostServices hostServicesParam) {
        this.hostServices = hostServicesParam;
    }

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

        final KeyFrame kf1 = new KeyFrame(Duration.millis(50), e -> populateImageView());
        final KeyFrame kf2 = new KeyFrame(Duration.millis(50), e ->
        {
            lblArticleTitle.setText(articleFromPreviousPage.getArticleTitle());
            lblArticleHeadingSnippet.setText(articleFromPreviousPage.getArticleTitleSnippet());
            lblArticleLeadParagraph.setText(articleFromPreviousPage.getArticleLeadParagraph());
            linkArticleOnline.setText(articleFromPreviousPage.getUrl());
            linkArticleOnline.setOnAction((ActionEvent event) -> {
                hostServices.showDocument(articleFromPreviousPage.getUrl());
            });
        });



        linkArticleOnline.setTooltip(new Tooltip("Go to this article's webpage"));

        final Timeline timeline = new Timeline(kf1, kf2);
        Platform.runLater(timeline::play);
    }

    /**
     * If the array of images returned for a specific article has no image(s) contained within, set a label to inform the user
     * Else perform the following:
     *  Fetch the relative url of the article image from the new york times api
     *  Prepend the relative url to make an absolute url to the article stored in the new york times
     *  Set the actual height of the imageview and container vertical box to the height and width of the article image
     *  Finally, set the image view image inside the fxml file to the retrieved image.
     */
    private void populateImageView() {
        if (articleFromPreviousPage.getMultimedia().length >= 1) {
            final ArticleImage imageLocation = articleFromPreviousPage.getMultimedia()[0];
            final String articleImageUrl = newYorkTimesImagePrepend+imageLocation.getArticleImageUrl();
            double imageWidth = articleFromPreviousPage.getMultimedia()[0].getWidth();
            double imageHeight = articleFromPreviousPage.getMultimedia()[0].getHeight();
            imgViewArticlePicture.setFitWidth(imageWidth);
            imgViewArticlePicture.setFitHeight(imageHeight);
            vboxForArticleImage.setMaxWidth(imageWidth);
            vboxForArticleImage.setMaxHeight(imageHeight);
            imgViewArticlePicture.setImage(new Image(articleImageUrl));
        } else {
            lblImageViewSuccess.setText("There was no image associated with this fetched article");
        }
    }
}
