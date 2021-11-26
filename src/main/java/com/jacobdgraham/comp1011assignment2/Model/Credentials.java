package com.jacobdgraham.comp1011assignment2.Model;

public class Credentials {

    private String api_key;
    private String api_secret;

    public Credentials(String api_key, String api_secret) {
        setApi_key(api_key);
        setApi_secret(api_secret);
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        if (api_key.equals("sLUaGVUJ77sKiYi5mOdTTnjc6W03nmpJ"))
        this.api_key = api_key;
        else {
            throw new IllegalArgumentException("Api key must be valid. Check in the apiKey_secretKey.json file to find it");
        }
    }

    public String getApi_secret() {
        return api_secret;
    }

    public void setApi_secret(String api_secret) {
        if (api_secret.equals("Oup2fCslkil7Vbj1"))
            this.api_secret = api_secret;
        else {
            throw new IllegalArgumentException("Api key must be valid. Check in the apiKey_secretKey.json file to find it");
        }
    }

    public String toString() {
        return String.format("Api key: %s | Api secret: %s", api_key, api_secret);
    }
}
