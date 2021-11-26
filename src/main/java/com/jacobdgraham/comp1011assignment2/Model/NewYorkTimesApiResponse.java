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

    public NewYorkTimesApiResponse getResponse() {
        return response;
    }

    public void setResponse(NewYorkTimesApiResponse response) {
        if (this.response != null) {
            this.response = response;
        } else {
            throw new IllegalArgumentException("The API response from the New York Times for Article Searching must not be null. There must be some values returned");
        }
    }

    public Article[] getDocs() {
        return docs;
    }

    public void setDocs(Article[] docs) {
        if (docs != null) {
            this.docs = docs;
        } else {
            throw new IllegalArgumentException("The list of documents from the New York Times for Article Searching must not be null");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.trim().matches("[a-zA-Z]{2,50}"))
        this.status = status;
        else {
            throw new IllegalArgumentException("The status returned from the New York Times API must be alphanumerical and be valid (must contain text)");
        }
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        if (copyright.trim().matches("[a-zA-Z]{2,50}")) {
            this.copyright = copyright;
        } else {
            throw new IllegalArgumentException("The copyright sentence returned from the New York Times API must be alphanumerical and valid (must contain text)");
        }
    }
}
