package com.jacobdgraham.comp1011assignment2.Model;

import com.google.gson.annotations.SerializedName;

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

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getUrl() {
        return articleUrl;
    }

    public void setUrl(String url) {
        this.articleUrl = url;
    }

    public String getArticleTitleSnippet() {
        return articleTitleSnippet;
    }

    public void setArticleTitleSnippet(String articleTitleSnippet) {
        this.articleTitleSnippet = articleTitleSnippet;
    }

    public String getArticleLeadParagraph() {
        return articleLeadParagraph;
    }

    public void setArticleLeadParagraph(String articleLeadParagraph) {
        this.articleLeadParagraph = articleLeadParagraph;
    }

    public String getArticleSource() {
        return articleSource;
    }

    public void setArticleSource(String articleSource) {
        this.articleSource = articleSource;
    }

    public String toString() {
        return String.format("Article title: %s, \n Url: %s \n Lead paragraph: %s\n", articleTitle, articleUrl, articleLeadParagraph);
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
