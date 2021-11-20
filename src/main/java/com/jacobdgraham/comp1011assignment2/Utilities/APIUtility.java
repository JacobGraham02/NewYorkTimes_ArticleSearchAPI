package com.jacobdgraham.comp1011assignment2.Utilities;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.jacobdgraham.comp1011assignment2.Model.Credentials;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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



}
