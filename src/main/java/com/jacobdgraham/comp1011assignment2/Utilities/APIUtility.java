package com.jacobdgraham.comp1011assignment2.Utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.jacobdgraham.comp1011assignment2.Model.Credentials;
import com.jacobdgraham.comp1011assignment2.Model.NewYorkTimesApiResponse;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class APIUtility {
    private static String api_key = "sLUaGVUJ77sKiYi5mOdTTnjc6W03nmpJ";
    private static String api_secret = "Oup2fCslkil7Vbj1";
    private static String jsonFileLocation = "articles_from_nyt_api.json";
    private static String jsonCredentialsFileLocation = "apiKey_secretKey.json";

    /**
     *
     * @return An array of size 2; location 0 containing api key, and location 1 containing api secret
     */
    public static String[] getCredentialsFromJsonInArray() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Credentials result;
        String[] credentials = new String[2];

        try (FileReader jsonFileReader = new FileReader(jsonCredentialsFileLocation);
             JsonReader jsonReader = new JsonReader(jsonFileReader)) {
            result = gson.fromJson(jsonReader, Credentials.class);
            credentials = new String[]{result.getApi_key(), result.getApi_secret()};

        } catch (IOException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    /**
     *
     * @param jsonFileLocation the json file to delete all contents from.
     * @throws IOException if a connection to file cannot be created, file cannot be found, or file cannot be written to.
     */
    private static void deleteJsonFileContents(String jsonFileLocation) throws IOException {
        Path filePath = Paths.get(jsonFileLocation);
        File file = new File(String.valueOf(filePath));
        try (BufferedOutputStream bufferedOutputStream =
                new BufferedOutputStream(new FileOutputStream(String.valueOf(file)))) {

            bufferedOutputStream.write("{}".getBytes());
            bufferedOutputStream.flush();
        }
    }

    /**
     *
     * @param keyword specific word used to query the API
     * @return an HttpRequest constructed and sent to the New York Times API.
     * @throws IOException
     */
    public static HttpRequest fetchAPIConnectionWithSpecificKeyword(String keyword) throws IOException {
        deleteJsonFileContents(jsonFileLocation);
        String uri = String.format("https://api.nytimes.com/svc/search/v2/articlesearch.json?q=%s&api-key=%s", keyword, api_key);
        uri = uri.replace(" ", "%20");

        HttpRequest requestForJsonData = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        return requestForJsonData;
    }

    /**
     *
     * @param httpRequest the HttpRequest object used to fetch data from and load into a JSON file.
     * @throws IOException if an input or output error occurs with the HttpRequest object.
     * @throws InterruptedException if a connection with the HttpRequest object is severed unexpectedly.
     */
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

    /**
     *
     * @return the actual API response object which will be displayed on the GUI.
     */
    public static NewYorkTimesApiResponse getArticlesFromJson() {
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
