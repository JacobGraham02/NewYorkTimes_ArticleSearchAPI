package com.jacobdgraham.comp1011assignment2.Model;

import com.google.gson.annotations.SerializedName;

public class Article {

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
        return String.format("Abstract: %s, \n Url: %s \n Lead paragraph: %s\n", articleTitle, articleUrl, articleLeadParagraph);
    }
}
