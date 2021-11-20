package com.jacobdgraham.comp1011assignment2.Model;

public class Credentials {

    private String api_key;
    private String api_secret;

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getApi_secret() {
        return api_secret;
    }

    public void setApi_secret(String api_secret) {
        this.api_secret = api_secret;
    }

    public String toString() {
        return String.format("Api key: %s | Api secret: %s", api_key, api_secret);
    }
}
