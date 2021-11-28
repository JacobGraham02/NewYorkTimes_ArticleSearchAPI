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

    /**
     * Returns the media (images) inside the article
     * @return array of images associated with an article(s)
     */
    public ArticleImage[] getMultimedia() {
        return multimedia;
    }

    /**
     * The array of images must not be null when assigning to a variable
     * @param multimedia an array of images associated with an article(s)
     * @throws IllegalArgumentException if the input array for article images is null
     */
    public void setMultimedia(ArticleImage[] multimedia) {
        if (multimedia != null) {
            this.multimedia = multimedia;
        } else {
            throw new IllegalArgumentException("Multimedia (article images) must not be null");
        }
    }

    /**
     *
     * @return String of the Article's title in a web browser
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * Uses Regex to set the article title
     * @param articleTitle a String
     * @throws IllegalArgumentException if the article title input string is not valid regex: [a-zA-Z0-9%]{2,50}
     */
    public void setArticleTitle(String articleTitle) {
        if (articleTitle.trim().replaceAll(" ","%20").matches("[a-zA-Z0-9%]{2,50}")) {
            this.articleTitle = articleTitle;
        } else {
            throw new IllegalArgumentException("The input string must be between 2 and 50 characters, and contain only letters, numbers, and spaces");
        }
    }

    /**
     *
     * @return the URL used in a web browser to traverse to the article
     */
    public String getUrl() {
        return articleUrl;
    }

    /**
     *
     * @param url a String modified using .trim().replaceAll(" ", "%20")
     * @throws IllegalArgumentException if the input string is not valid regex: [a-zA-Z0-9%-_]{2,50}
     */
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

    /**
     *
     * @param articleTitleSnippet a String modified using .trim() .replaceAll(" ", "%20")
     * @throws IllegalArgumentException if the input string is not valid regex: [a-zA-Z0-9!@#$%^&*()]{2,50}
     */
    public void setArticleTitleSnippet(String articleTitleSnippet) {
        if (articleTitleSnippet.trim().matches("[a-zA-Z0-9!@#$%^&*() ]{2,50}")) {
            this.articleTitleSnippet = articleTitleSnippet;
        } else {
            throw new IllegalArgumentException("The input string must be between 2 and 50 characters, " +
                    "and contain only letters, numbers, spaces, and these characters: ! @ # $ % ^ & * ( )");
        }
    }

    /**
     *
     * @return String of the currently referenced Article's first occuring paragraph
     */
    public String getArticleLeadParagraph() {
        return articleLeadParagraph;
    }

    /**
     *
     * @param articleLeadParagraph a String modified using .trim()
     * @throws IllegalArgumentException if input string is not valid regex: [a-zA-Z0-9!@#$%^&*()]{2,50}
     */
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

    /**
     *
     * @param articleSource a String modified using .trim()
     * @throws IllegalArgumentException if input string is not valid regex [a-zA-Z0-9!@#$%^&*()]{2,50}
     */
    public void setArticleSource(String articleSource) {
        if (articleSource.trim().matches("[a-zA-Z0-9!@#$%^&*() ]{2,50}")) {
            this.articleSource = articleSource;
        } else {
            throw new IllegalArgumentException("The input string must be between 2 and 50 characters, " +
                    "and contain only letters, numbers, spaces, and these characters: ! @ # $ % ^ & * ( )");
        }
    }

    /**
     *
     * @return a String which displays the following information in the following format:
     * Article title: %s
     * Url: %s
     * Lead paragraph: %s
     * %s (article image url without http or https protocol)
     */
    public String toString() {
        return String.format("Article title: %s, \n Url: %s \n Lead paragraph: %s\n %s \n", articleTitle, articleUrl, articleLeadParagraph, multimedia[0]);
    }

    /**
     *
     * @param o an Article to compare with the one referenced by the current class
     * @return an integer value based on the Java's TreeMap native implementation to order Nodes.
     */
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
