package com.example.lazylibrary;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.net.*;

import java.util.Iterator;
import java.util.Scanner;

@SpringBootApplication
public class LazyLibraryApplication {

    public static void main(String[] args) throws IOException, ParseException {
        SpringApplication.run(LazyLibraryApplication.class, args);
        Scanner sc = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator();
        do {
            System.out.println("Welcome to lazy library");
            //Start GET
            try {
                URL url = new URL("http://localhost:8080/ListBooks");

                HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
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
                    JSONArray jsonArray = (JSONArray) jsonParser.parse(String.valueOf(jsonString));
                    System.out.println("AVAILABLE BOOKS:");
                    Iterator<Object> iterator = jsonArray.iterator();
                    while(iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                }
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
            //End GET
            int choice = 0;
            choice = inputValidator.inputValidator(choice,"Press 1. to ADD a book\nPress 2. to DELETE a book\nPress 3. to REPLACE a book");
            switch (choice){
                case 1:
                    try {
                        System.out.println("Book name: ");
                        String name = sc.nextLine();
                        System.out.println("Author: ");
                        String author = sc.nextLine();
                        System.out.println("Number of pages");
                        String numberOfPages = sc.nextLine();
                        System.out.println("Publication date");
                        String publicationDate = sc.nextLine();
                        URL url = new URL("http://localhost:8080/AddBook?name="+name+"&author="+author+"&numberOfPages="+numberOfPages+"&publicationDate="+publicationDate);

                        HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
                        getConnection.setRequestMethod("POST");
                        getConnection.connect();

                        int code = getConnection.getResponseCode();
                        if (code > 199 && code < 300) {
                            System.out.println("success");
                        }else{
                            System.out.println("the post failed");
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Type in the name of the book you want to delete");
                        String name = sc.nextLine();
                        URL url = new URL("http://localhost:8080/DeleteBook?name="+name);

                        HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
                        getConnection.setRequestMethod("DELETE");
                        getConnection.connect();

                        int code = getConnection.getResponseCode();
                        if (code > 199 && code < 300) {
                            System.out.println("success");
                        }else{
                            System.out.println("the delete failed");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Type in the name of the book to be replaced: ");
                        String bookToBeReplaced = sc.nextLine();
                        System.out.println("Name of the book to be added: ");
                        String name = sc.nextLine();
                        System.out.println("Author: ");
                        String author = sc.nextLine();
                        System.out.println("Number of pages");
                        String numberOfPages = sc.nextLine();
                        System.out.println("Publication date");
                        String publicationDate = sc.nextLine();
                        URL url = new URL("http://localhost:8080/ReplaceBook?nameOfTheBookToBeReplaced="+bookToBeReplaced+"&name="+name+"&author="+author+"&numberOfPages="+numberOfPages+"&publicationDate="+publicationDate);

                        HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
                        getConnection.setRequestMethod("PUT");
                        getConnection.connect();

                        int code = getConnection.getResponseCode();
                        if (code > 199 && code < 300) {
                            System.out.println("success");
                        }else{
                            System.out.println("the put failed");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }

        }while(true);
    }

}


