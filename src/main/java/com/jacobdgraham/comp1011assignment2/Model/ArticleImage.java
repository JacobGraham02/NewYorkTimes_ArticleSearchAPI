package com.jacobdgraham.comp1011assignment2.Model;

import com.google.gson.annotations.SerializedName;

public class ArticleImage {

    @SerializedName("url")
    private String articleImageUrl;

    @SerializedName("height")
    private int height;

    @SerializedName("width")
    private int width;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getArticleImageUrl() {
        return articleImageUrl;
    }

    public void setArticleImageUrl(String articleImageUrl) {
        this.articleImageUrl = articleImageUrl;
    }

    public String toString() {
        return String.format("%s", getArticleImageUrl());
    }
}
