package com.jacobdgraham.comp1011assignment2.Utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.jacobdgraham.comp1011assignment2.Model.Credentials;
import com.jacobdgraham.comp1011assignment2.Model.NewYorkTimesApiResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class APIUtility {
    private static String api_key = "sLUaGVUJ77sKiYi5mOdTTnjc6W03nmpJ";
    private static String api_secret = "Oup2fCslkil7Vbj1";
    private static String jsonFileLocation = "articles_from_nyt_api.json";

    public static String[] getCredentialsFromJsonInArray(String file_path) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
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

    public static HttpRequest fetchAPIConnection() {
        String uri = String.format("https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=%s", api_key);

        HttpRequest requestForJsonData = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        return requestForJsonData;
    }

    public static void fetchApiResultsInJsonFile(HttpRequest httpRequest) throws IOException, InterruptedException {
        HttpResponse<Path> responseOfJsonData = HttpClient.newHttpClient().send
                (httpRequest, HttpResponse.BodyHandlers.ofFile(Paths.get(jsonFileLocation)));
    }
//
//    public static void writeDataToJson(String data, String fileLocation) throws IOException {
//        try (Writer writer = new FileWriter(fileLocation)) {
//            Gson gson = new GsonBuilder().create();
//            gson.toJson(data, writer);
//        }
    // To get image path for article image, enter the URL by appending http://www.nytimes.com/ to each of the URL's returned by the API.  https://www.nytimes.com/images/2021/09/29/world/29japan-ldp-election-winner1/29japan-ldp-election-winner1-blog480-v3.jpg
    public static NewYorkTimesApiResponse getArticlesFromJson() throws IOException {
        Gson gson = new Gson();
        NewYorkTimesApiResponse newYorkTimesApiResponse = null;

        try (FileReader newYorkTimesApiFileReader = new FileReader(jsonFileLocation);
        JsonReader newYorkTimesApiJsonReader = new JsonReader(newYorkTimesApiFileReader);) {

            newYorkTimesApiResponse = gson.fromJson(newYorkTimesApiJsonReader, NewYorkTimesApiResponse.class);
            newYorkTimesApiResponse.setDocs(newYorkTimesApiResponse.getResponse().getDocs());

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return newYorkTimesApiResponse;
    }
}
