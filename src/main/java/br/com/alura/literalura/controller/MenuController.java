package br.com.alura.literalura.controller;

import java.lang.Float;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.literalura.dto.AuthorDto;
import br.com.alura.literalura.dto.BookDto;
import br.com.alura.literalura.dto.ResponseDto;
import br.com.alura.literalura.model.Author;
import br.com.alura.literalura.model.Book;
import br.com.alura.literalura.service.AuthorService;
import br.com.alura.literalura.service.BookService;
import br.com.alura.literalura.service.HttpClientService;

@Controller
public class MenuController {
    private final Scanner scanner;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private HttpClientService httpClientService;

    public MenuController() {
        this.scanner = new Scanner(System.in);
    }

    private void searchBook() {
        final String ORIGIN_URL = "https://gutendex.com/books/?search=";

        System.out.print("Search book by title or author> ");
        String search = scanner.nextLine();

        String resolvedUrl = ORIGIN_URL + search.replace(" ", "%20");
        String resJson = httpClientService.sendRequest(resolvedUrl);

        ObjectMapper mapper = new ObjectMapper();
        try {
            ResponseDto resDto = mapper.readValue(resJson, ResponseDto.class);
            if (resDto.count() == 0) {
                return;
            }
            BookDto bookDto = resDto.results().get(0);

            // Save Book and Authors entities
            Book book = new Book(bookDto);
            bookService.save(book);

            List<Author> authors = bookDto.authors().stream()
                    .map(authorDto -> new Author(authorDto))
                    .collect(Collectors.toList());
            authorService.saveAll(authors);

            // Establish the relationship between Book and its Authors
            book.setAuthors(authors);
            authors.forEach(author -> author.getBooks().add(book));

            // Save the Book so it reflects on Authors
            bookService.save(book);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void listBooks() {
        List<BookDto> books = bookService.findAll();
        books.forEach(System.out::println);
    }

    private void listAuthors() {
        List<AuthorDto> authorsDto = authorService.findAll();
        authorsDto.forEach(System.out::println);
    }

    private void listAuthorsAliveAtYear() {
        System.out.print("List authors alive at the year> ");
        Integer year = scanner.nextInt();
        scanner.nextLine();

        List<AuthorDto> authorsDto = authorService.findAuthorsAliveAt(year);
        authorsDto.forEach(System.out::println);
    }

    private void showBooksCountByLanguage() {
        List<BookDto> books = bookService.findAll();

        Map<String, Long> bookLanguageCounts = books.stream()
            .flatMap(book -> Arrays.stream(book.languages()))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        bookLanguageCounts.forEach((language, count) ->
            System.out.println(language + " -> " + count)
        );
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("1: Search book by title or author");
            System.out.println("2: List books");
            System.out.println("3: List authors");
            System.out.println("4: List authors alive at year");
            System.out.println("5: Show books count by language");
            System.out.print("Choose an option or 'exit' to leave> ");
            String option = scanner.nextLine();
            if (option.equals("exit")) {
                break;
            }
            System.out.println();
            switch (option) {
                case "1":
                    searchBook();
                    break;
                case "2":
                    listBooks();
                    break;
                case "3":
                    listAuthors();
                    break;
                case "4":
                    listAuthorsAliveAtYear();
                    break;
                case "5":
                    showBooksCountByLanguage();
                    break;
                default:
                    break;
            }
            System.out.println();
            System.out.print("Press any key to go back to menu...");
            scanner.nextLine();
        }
    }
}
