package com.jacobdgraham.comp1011assignment2.Model;

public class Credentials {

    private String api_key;
    private String api_secret;

    public Credentials(String api_key, String api_secret) {
        setApi_key(api_key);
        setApi_secret(api_secret);
    }

    /**
     *
     * @return a String value of my api key used to access the New York Times article search API.
     */
    public String getApi_key() {
        return api_key;
    }

    /**
     * My api key for the New York Times article search API is static, and therefore never changes.
     * @param api_key a String value of my api key used to access the New York Times article search API.
     * @throws IllegalArgumentException if the api key used to access the New York Times article search API is not a valid key.
     * I.e., not sLUaGVUJ77sKiYi5mOdTTnjc6W03nmpJ
     */
    public void setApi_key(String api_key) {
        if (api_key.equals("sLUaGVUJ77sKiYi5mOdTTnjc6W03nmpJ"))
        this.api_key = api_key;
        else {
            throw new IllegalArgumentException("Api key must be valid. Check in the apiKey_secretKey.json file to find it");
        }
    }

    /**
     *
     * @return a String value of my api secret used to access the New York Times article search API.
     */
    public String getApi_secret() {
        return api_secret;
    }

    /**
     *
     * @param api_secret a String value of my api secret used to access the New York Times article search API
     * @throws IllegalArgumentException if the api secret used to access the New York Times article search API is not a valid secret.
     * I.e., not Oup2fCslkil7Vbj1
     */
    public void setApi_secret(String api_secret) {
        if (api_secret.equals("Oup2fCslkil7Vbj1"))
            this.api_secret = api_secret;
        else {
            throw new IllegalArgumentException("Api key must be valid. Check in the apiKey_secretKey.json file to find it");
        }
    }

    /**
     *
     * @return a String value which displays both the api key and api secret in the following format:
     * Api key: %s | Api secret: %s
     */
    public String toString() {
        return String.format("Api key: %s | Api secret: %s", api_key, api_secret);
    }
}
