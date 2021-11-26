package com.jacobdgraham.comp1011assignment2.Model;

import com.google.gson.annotations.SerializedName;

public class ArticleImage {

    @SerializedName("url")
    private String articleImageUrl;

    @SerializedName("height")
    private int height;

    @SerializedName("width")
    private int width;

    public ArticleImage(String url, int width, int height) {
        setArticleImageUrl(url);
        setWidth(width);
        setHeight(height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height > 0) {
            this.height = height;
        } else {
            throw new IllegalArgumentException("Height of image must be greater than 0");
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width > 0) {
            this.width = width;
        } else {
            throw new IllegalArgumentException("Width of image must be greater than 0");
        }
    }

    public String getArticleImageUrl() {
        return articleImageUrl;
    }

    public void setArticleImageUrl(String articleImageUrl) {
        if (articleImageUrl.trim().replace(" ", "%20").matches("[a-zA-Z0-9!@#$%^&*() ]{2,50}"))
        this.articleImageUrl = articleImageUrl;
        else
            throw new IllegalArgumentException("The article image url must be between 2 and 50 characters, \" +\n" +
                    "                    \"and contain only letters, numbers, spaces, and these characters: ! @ # $ % ^ & * ( )");
    }

    public String toString() {
        return String.format("%s", getArticleImageUrl());
    }
}
