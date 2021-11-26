package com.jacobdgraham.comp1011assignment2.Model;

import com.google.gson.annotations.SerializedName;
import javafx.scene.image.Image;

import java.util.Comparator;

public class Article implements Comparable<Article> {

    @SerializedName("abstract")
    private String articleTitle;

    @SerializedName("web_url")
    private String articleUrl;

    @SerializedName("snippet")
    private String articleTitleSnippet;

    @SerializedName("lead_paragraph")
    private String articleLeadParagraph;

    @SerializedName("source")
    private String articleSource;

    @SerializedName("multimedia")
    private ArticleImage[] multimedia;

    public Article(String title, String url, String titleSnippet, String leadParagraph, String source, ArticleImage[] images) {
        setArticleTitle(title);
        setUrl(url);
        setArticleTitleSnippet(titleSnippet);
        setArticleLeadParagraph(leadParagraph);
        setArticleSource(source);
        setMultimedia(images);
    }

    public ArticleImage[] getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(ArticleImage[] multimedia) {
        if (multimedia != null) {
            this.multimedia = multimedia;
        } else {
            throw new IllegalArgumentException("Multimedia (article images) must not be null");
        }
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    //return txtKeywords.getText().trim().replaceAll(" ", "%20").matches("[a-zA-Z0-9%]{2,50}");
    public void setArticleTitle(String articleTitle) {
        if (articleTitle.trim().replaceAll(" ","%20").matches("[a-zA-Z0-9%]{2,50}")) {
            this.articleTitle = articleTitle;
        } else {
            throw new IllegalArgumentException("The input string must be between 2 and 50 characters, and contain only letters, numbers, and spaces");
        }
    }

    public String getUrl() {
        return articleUrl;
    }

    public void setUrl(String url) {
        if (url.trim().replaceAll(" ", "%20").matches("[a-zA-Z0-9%-_]{2,50}")) {
            this.articleUrl = url;
        } else {
            throw new IllegalArgumentException("The input string must be between 2 and 50 characters and contain only letters, numbers, and spaces");
        }
    }

    public String getArticleTitleSnippet() {
        return articleTitleSnippet;
    }

    public void setArticleTitleSnippet(String articleTitleSnippet) {
        if (articleTitleSnippet.trim().matches("[a-zA-Z0-9!@#$%^&*() ]{2,50}")) {
            this.articleTitleSnippet = articleTitleSnippet;
        } else {
            throw new IllegalArgumentException("The input string must be between 2 and 50 characters, " +
                    "and contain only letters, numbers, spaces, and these characters: ! @ # $ % ^ & * ( )");
        }
    }

    public String getArticleLeadParagraph() {
        return articleLeadParagraph;
    }

    public void setArticleLeadParagraph(String articleLeadParagraph) {
        if (articleLeadParagraph.trim().matches("[a-zA-Z0-9!@#$%^&*() ]{2,50}")) {
            this.articleLeadParagraph = articleLeadParagraph;
        } else {
            throw new IllegalArgumentException("The input string must be between 2 and 50 characters, " +
                    "and contain only letters, numbers, spaces, and these characters: ! @ # $ % ^ & * ( )");
        }
    }

    public String getArticleSource() {
        return articleSource;
    }

    public void setArticleSource(String articleSource) {
        if (articleSource.trim().matches("[a-zA-Z0-9!@#$%^&*() ]{2,50}")) {
            this.articleSource = articleSource;
        } else {
            throw new IllegalArgumentException("The input string must be between 2 and 50 characters, " +
                    "and contain only letters, numbers, spaces, and these characters: ! @ # $ % ^ & * ( )");
        }
    }

    public String toString() {
        return String.format("Article title: %s, \n Url: %s \n Lead paragraph: %s\n %s \n", articleTitle, articleUrl, articleLeadParagraph, multimedia[0]);
    }

    @Override
    public int compareTo(Article o) {
        // Compare values with ASCII table. In this example, if o.charAt(0) is R, and this.charAt(0) is B, 16 will be returned; B is 16 chars from R
        if (this.articleTitle.charAt(0) > o.articleTitle.charAt(0)) {
            return 1;
        } else if (this.articleTitle.charAt(0) < o.articleTitle.charAt(0)){
            return 0;
        } else {
            return -1;
        }
    }
}
