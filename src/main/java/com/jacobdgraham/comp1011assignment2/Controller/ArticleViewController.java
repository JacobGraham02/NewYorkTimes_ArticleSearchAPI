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

import static com.jacobdgraham.comp1011assignment2.Utilities.APIUtility.getArticlesFromJson;
import static com.jacobdgraham.comp1011assignment2.Utilities.APIUtility.getCredentialsFromJsonInArray;

public class ArticleViewController implements Initializable {

    private String[] stringArrayArticleSearchCredentials;
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

    @FXML
    void searchForArticles(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stringArrayArticleSearchCredentials = new String[2];
        treeMapNewYorkTimesArticles = new TreeSet<>();
        stringArrayArticleSearchCredentials = getCredentialsFromJsonInArray("apiKey_secretKey.json");

        try {
            treeMapNewYorkTimesArticles.addAll(Arrays.asList(getArticlesFromJson().getDocs()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        tblViewColumnArticleTitle.setCellValueFactory(new PropertyValueFactory<>("articleTitle"));
//        tblViewColumnArticleSource.setCellValueFactory(new PropertyValueFactory<>("articleSource"));

        for (Article article : treeMapNewYorkTimesArticles) {
            tblViewArticleTitles.getItems().add(article);
        }

        treeMapNewYorkTimesArticles.stream().forEach(System.out::println);



        try {
            System.out.println(getArticlesFromJson().getDocs()[0].getArticleTitle()); // Object of type Article
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
