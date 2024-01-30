package org.example.service.impl;

import com.google.gson.Gson;
import org.example.domain.DateApi;
import org.example.service.DateService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DateServiceImpl implements DateService {

    @Override
    public void printDateInformation(Integer month, Integer day) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://numbersapi.p.rapidapi.com/%s/%s/date?fragment=true&json=true".formatted(month, day)))
                .header("X-RapidAPI-Key", "af9116a428msh4b750dd4631aa07p138e80jsna48c4dd95f88")
                .header("X-RapidAPI-Host", "numbersapi.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();

        DateApi dateApi = gson.fromJson(response.body(), DateApi.class);

        System.out.println(dateApi.getText());
    }

    private static DateServiceImpl dateService;

    public static DateServiceImpl getInstance(){
        if (dateService == null){
            dateService = new DateServiceImpl();
            return dateService;
        }
        return dateService;
    }
}
