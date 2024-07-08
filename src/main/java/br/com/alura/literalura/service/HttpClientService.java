package br.com.alura.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

@Service
public class HttpClientService
{
    private final HttpClient httpClient;

    public HttpClientService()
    {
        this.httpClient = HttpClient.newHttpClient();
    }

    // NOTE: Maybe return an Optional<String>
    // instead of String | null
    public String sendRequest(String url)
    {
        HttpRequest request = HttpRequest.newBuilder()
           .uri(URI.create(url))
           .build();

        try
        {
            HttpResponse<String> res = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return res.body();
        }
        catch (IOException | InterruptedException e)
        {
            System.out.println(e.getCause());
        }
        return null;
    }
}
