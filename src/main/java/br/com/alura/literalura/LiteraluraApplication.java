package br.com.alura.literalura;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.literalura.dto.ResponseDto;
import br.com.alura.literalura.service.HttpClientService;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner
{
    private String originUrl = "https://gutendex.com/books/?search=";

    public static void main(String[] args)
    {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        try (Scanner s = new Scanner(System.in))
        {
            while (true)
            {
                System.out.println("----- Option Menu -------");
                System.out.println("1. Search by Title/Author");
                System.out.print("Choose an option: ");
                String option = s.nextLine();
                if (option.equals("exit"))
                {
                    break ;
                }
                //MenuService.handleInput(option);

                HttpClientService httpClientService = new HttpClientService();
                String resJson = httpClientService.sendRequest(originUrl + option.replace(" ", "%20"));

                // Deserialize JSON String to Java Object
                ObjectMapper objMapper = new ObjectMapper();
                ResponseDto resDTO = objMapper.readValue(resJson, ResponseDto.class);

                resDTO.results().forEach(System.out::println);
            }
        }
    }
}
