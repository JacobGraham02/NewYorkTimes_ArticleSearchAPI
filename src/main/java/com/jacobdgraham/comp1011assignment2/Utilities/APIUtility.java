package com.jacobdgraham.comp1011assignment2.Utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.jacobdgraham.comp1011assignment2.Model.Credentials;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class APIUtility {

    public static String[] getCredentialsFromJsonInArray(String file_path) {
        Gson gson = new Gson();
        Credentials result = null;
        String[] credentials = new String[2];

        try (FileReader jsonFileReader = new FileReader(file_path);
             JsonReader jsonReader = new JsonReader(jsonFileReader)) {
            result = gson.fromJson(jsonReader, Credentials.class);
            credentials = new String[]{result.getApi_key(), result.getApi_secret()};

        } catch (IOException e) {
            e.printStackTrace();
        }
        return credentials;
    }
    private static HttpURLConnection fetchAPIConnection() throws IOException {
        URL url = new URL("https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=sLUaGVUJ77sKiYi5mOdTTnjc6W03nmpJ");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.connect();

        int connectionResponseCode = connection.getResponseCode();

        if (connectionResponseCode != 200) {
            throw new RuntimeException("Http response code is not 200, indicating failure. It is instead: " + connectionResponseCode);
        }
        return connection;
    }

    public static String fetchAPIResultsInString() throws IOException {
        StringBuilder jsonToParse = new StringBuilder();

        try (Scanner scanner = new Scanner(fetchAPIConnection().getURL().openStream())) {
            while (scanner.hasNext()) {
                jsonToParse.append(scanner.nextLine());
            }
        }
        return jsonToParse.toString();
    }

    public static void writeDataToArticlesJson(String data) throws IOException {
        try (Writer writer = new FileWriter("articles_by_keyword.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(data, writer);
        }

    }
}
