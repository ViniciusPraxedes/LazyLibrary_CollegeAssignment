package com.example.lazylibrary;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.*;

import java.util.Iterator;

@SpringBootApplication
public class LazyLibraryApplication {

    public static void main(String[] args) throws IOException, ParseException {
        SpringApplication.run(LazyLibraryApplication.class, args);

        try {
            URL url = new URL("http://localhost:8080/books");

            HttpURLConnection getConnection = (HttpURLConnection) url.openConnection(); // This already connects.
            getConnection.setRequestMethod("GET");

            int code = getConnection.getResponseCode();
            if (code > 199 && code < 300) {
                BufferedReader in = new BufferedReader(new InputStreamReader(getConnection.getInputStream()));
                String inputLine;
                StringBuilder jsonString = new StringBuilder();

                while((inputLine = in.readLine()) != null) {
                    jsonString.append(inputLine); 
                }
                in.close();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(String.valueOf(jsonString));
                JSONArray jsonArray = (JSONArray) jsonObject.get("books");


                Iterator<String> iterator = jsonArray.iterator();
                while(iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

}


