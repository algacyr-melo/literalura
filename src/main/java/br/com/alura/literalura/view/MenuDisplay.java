package br.com.alura.literalura.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.literalura.dto.BookDto;
import br.com.alura.literalura.dto.ResponseDto;
import br.com.alura.literalura.model.Author;
import br.com.alura.literalura.model.Book;
import br.com.alura.literalura.service.BookService;
import br.com.alura.literalura.service.HttpClientService;

@Service
public class MenuDisplay
{
    private final Scanner scanner;

    @Autowired
    private BookService bookService;

    @Autowired
    private HttpClientService httpClientService;

    public MenuDisplay()
    {
        this.scanner = new Scanner(System.in);
    }

    private void searchBook()
    {
        String originUrl = "https://gutendex.com/books/?search=";
        System.out.print("Search book by title or author> ");
        String search = scanner.nextLine();

        var resJson = httpClientService.sendRequest(originUrl+search.replace(" ", "%20"));

        ObjectMapper mapper = new ObjectMapper();
        try
        {
            // Deserialize from Json String to an Object
            ResponseDto resDto = mapper.readValue(resJson, ResponseDto.class);

            // Get the first book result
            BookDto bookDto = resDto.results().get(0);

            List<Author> authors = bookDto.authors().stream()
                .map(authorDto -> new Author(authorDto))
                .collect(Collectors.toList());

            Book book = new Book(bookDto, authors);
            bookService.saveBook(book);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
    }

    public void start()
    {
        while (true)
        {
            System.out.println("1: Search book by title or author");
            System.out.print("Choose an option or 'exit' to leave> ");
            String option = scanner.nextLine();
            if (option.equals("exit"))
            {
                break ;
            }
            switch (option)
            {
                case "1":
                    searchBook();
                    break;

                default:
                    break;
            }
        }
    }
}
