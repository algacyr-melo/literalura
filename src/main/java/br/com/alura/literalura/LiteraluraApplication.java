package br.com.alura.literalura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.literalura.service.HttpClientHandler;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner
{
    public static void main(String[] args)
    {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        HttpClientHandler httpClientHandler = new HttpClientHandler();

        String res = httpClientHandler.sendRequest("https://gutendex.com/books/1/");
        System.out.println(res);
    }
}
