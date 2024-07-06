package br.com.alura.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public class HttpClientHandler
{
    HttpClient client = HttpClient.newHttpClient();

    public CompletableFuture<String> sendRequestAsync(String url)
    {
        HttpRequest request = HttpRequest.newBuilder()
           .uri(URI.create(url))
           .build();

        CompletableFuture<String> res = client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body);

        return res;
    }

    public String sendRequest(String url)
    {
        HttpRequest request = HttpRequest.newBuilder()
           .uri(URI.create(url))
           .build();

        try
        {
            HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());
            return res.body();
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
