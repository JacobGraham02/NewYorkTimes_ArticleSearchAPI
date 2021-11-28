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

    /**
     *
     * @return an integer representing the height of the image associated with the article
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height an integer which represents the height of the image
     * @throws IllegalArgumentException if the height of the image is less than the integer value 0
     */
    public void setHeight(int height) {
        if (height > 0) {
            this.height = height;
        } else {
            throw new IllegalArgumentException("Height of image must be greater than 0");
        }
    }

    /**
     *
     * @return an integer value representing the width of the article image
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @param width an integer value representing the width of the article image
     * @throws IllegalArgumentException if the width of the image is less than the integer value of 0
     */
    public void setWidth(int width) {
        if (width > 0) {
            this.width = width;
        } else {
            throw new IllegalArgumentException("Width of image must be greater than 0");
        }
    }

    /**
     *
     * @return a String which holds the New York Times article image, excluding the protocol used to create a request to get the image.
     */
    public String getArticleImageUrl() {
        return articleImageUrl;
    }

    /**
     *
     * @param articleImageUrl a String value which holds the location of the New York Times article image, excluding the protocol used to request the image.
     * @throws IllegalArgumentException if the url of the image does not match the following regex: [a-zA-Z0-9!@#$%^&*() ]{2,50}
     */
    public void setArticleImageUrl(String articleImageUrl) {
        if (articleImageUrl.trim().replace(" ", "%20").matches("[a-zA-Z0-9!@#$%^&*() ]{2,50}"))
        this.articleImageUrl = articleImageUrl;
        else
            throw new IllegalArgumentException("The article image url must be between 2 and 50 characters, \" +\n" +
                    "                    \"and contain only letters, numbers, spaces, and these characters: ! @ # $ % ^ & * ( )");
    }

    /**
     *
     * @return a String which displays the url of the New York Times article image, excluding the protocol used to create a request, in the following format:
     * %s (the article image url excluding any http or https protocol)
     */
    public String toString() {
        return String.format("%s", getArticleImageUrl());
    }
}
