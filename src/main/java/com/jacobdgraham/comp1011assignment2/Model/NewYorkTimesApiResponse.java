package com.jacobdgraham.comp1011assignment2.Model;

import com.google.gson.annotations.SerializedName;

public class NewYorkTimesApiResponse {

    private String status;

    private String copyright;

    private NewYorkTimesApiResponse response;

    @SerializedName("docs")
    private Article[] docs;

    public NewYorkTimesApiResponse getResponse() {
        return response;
    }

    public void setResponse(NewYorkTimesApiResponse response) {
        this.response = response;
    }

    public Article[] getDocs() {
        return docs;
    }

    public void setDocs(Article[] docs) {
        this.docs = docs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
