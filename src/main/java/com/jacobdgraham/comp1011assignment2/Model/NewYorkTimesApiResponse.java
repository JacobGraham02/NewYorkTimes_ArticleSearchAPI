package com.jacobdgraham.comp1011assignment2.Model;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

public class NewYorkTimesApiResponse {

    private String status;

    private String copyright;

    private NewYorkTimesApiResponse response;

    @SerializedName("docs")
    private Article[] docs;

    public NewYorkTimesApiResponse(String status, String copyright, NewYorkTimesApiResponse response, Article[] docs) {
        setStatus(status);
        setCopyright(copyright);
        setResponse(response);
        setDocs(docs);
    }

    /**
     *
     * @return a custom NewYorkTimesApiResponse object which contains Article Search API data read from the New York Times JSON response.
     *
     */
    public NewYorkTimesApiResponse getResponse() {
        return response;
    }

    /**
     *
     * @param response a custom NewYorkTimesApiResponse object which contains Article Search API data read from the New York Times JSON response.
     * @throws IllegalArgumentException if the NewYorkTimesApiResponse from the Article Search API is null; i.e. does not get a response at all from the API.
     */
    public void setResponse(NewYorkTimesApiResponse response) {
        if (this.response != null) {
            this.response = response;
        } else {
            throw new IllegalArgumentException("The API response from the New York Times for Article Searching must not be null. There must be some values returned");
        }
    }

    /**
     *
     * @return an array of custom Article objects which contain the following article information:
     * Article title
     * Article web url
     * Article title snippet
     * Article leading paragraph snippet
     * The source of where the article came from
     * An array of ArticleImage[] objects which only contain the image(s) used in a specific article.
     */
    public Article[] getDocs() {
        return docs;
    }

    /**
     *
     * @param docs an array of custom Article objects which contain the following article information
     * Article title
     * Article web url
     * Article title snippet
     * Article leading paragraph snippet
     * The source of where the article came from
     * An array of ArticleImage[] objects which only contain the image(s) used in a specific article.
     * @throws IllegalArgumentException if there were no custom Article objects constructed from the New York Times article search API JSON response.
     */
    public void setDocs(Article[] docs) {
        if (docs != null) {
            this.docs = docs;
        } else {
            throw new IllegalArgumentException("The list of documents from the New York Times for Article Searching must not be null");
        }
    }

    /**
     *
     * @return a String value representing the response status of the JSON data from the New York Times article search API.
     * i.e. "status": "OK"
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status a String value representing the response status of the JSON data from the New York Times article search API. I.e. "status": "OK"
     * @throws IllegalArgumentException if the String value of the JSON response status does not match the following regex: [a-zA-Z ]{2,50}
     */
    public void setStatus(String status) {
        if (status.trim().matches("[a-zA-Z0-9() ]{2,50}"))
        this.status = status;
        else {
            throw new IllegalArgumentException("The status returned from the New York Times API must be alphanumerical and be valid (must contain text)");
        }
    }

    /**
     *
     * @return a String value of the copyright retrieved from the JSON response from the New York Times article search API.
     * I.e. "Copyright (c) 2021 The New York Times Company. All Rights Reserved.
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     *
     * @param copyright a String value of the copyright retrieved from the JSON response from the New York Times article search API.
     * I.e. "Copyright (c) 2021 The New York Times Company. All Rights Reserved.
     * @throws IllegalArgumentException if the String value of copyright from the JSON response does not match the following regex: [a-zA-Z ]{2,50}
     */
    public void setCopyright(String copyright) {
        if (copyright.trim().matches("[a-zA-Z0-9() ]{2,50}")) {
            this.copyright = copyright;
        } else {
            throw new IllegalArgumentException("The copyright sentence returned from the New York Times API must be alphanumerical and valid (must contain text)");
        }
    }
}
