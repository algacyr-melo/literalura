package br.com.alura.literalura.controller;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.literalura.dto.BookDto;
import br.com.alura.literalura.dto.ResponseDto;
import br.com.alura.literalura.model.Author;
import br.com.alura.literalura.model.Book;
import br.com.alura.literalura.service.AuthorService;
import br.com.alura.literalura.service.BookService;
import br.com.alura.literalura.service.HttpClientService;

@Controller
public class MenuController
{
    private final Scanner scanner;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private HttpClientService httpClientService;

    public MenuController()
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
            // Deserialize from json string to an object
            ResponseDto resDto = mapper.readValue(resJson, ResponseDto.class);

            // Get the first book result data
            BookDto bookDto = resDto.results().get(0);

            // Get the book's authors
            List<Author> authors = bookDto.authors().stream()
                .map(authorDto -> new Author(authorDto))
                .collect(Collectors.toList());

            // Save authors on DB
            authors.forEach(author -> authorService.saveAuthor(author));

            // Create the book instance with its authors
            Book book = new Book(bookDto, authors);

            // Set the book for each author and save the book on DB
            authors.forEach(author -> author.getBooks().add(book));
            bookService.saveBook(book);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
    }

    public void listBooks()
    {
        List<BookDto> books = bookService.getBooksAll();
        books.forEach(System.out::println);
    }

    public void start()
    {
        while (true)
        {
            System.out.println("1: Search book by title or author");
            System.out.println("2: List all books");
            System.out.print("Choose an option or 'exit' to leave> ");
            String option = scanner.nextLine();
            if (option.equals("exit"))
            {
                break;
            }
            switch (option)
            {
                case "1":
                    searchBook();
                    break;
                case "2":
                    listBooks();
                    break;
                default:
                    break;
            }
        }
    }
}
