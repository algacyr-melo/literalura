package br.com.alura.literalura.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.alura.literalura.dto.AuthorDto;
import br.com.alura.literalura.dto.BookDto;
import br.com.alura.literalura.model.Book;
import br.com.alura.literalura.repository.BookRepository;
import jakarta.transaction.Transactional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public void save(Book book) {
        try {
            bookRepository.save(book);
        } catch (DataIntegrityViolationException e) {
        }
    }

    @Transactional
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();

        return books.stream()
            .map(book -> new BookDto(
                book.getTitle(),
                book.getAuthors().stream()
                    .map(author -> new AuthorDto(
                        author.getName(),
                        author.getBirthYear(),
                        author.getDeathYear())
                    ).collect(Collectors.toList()),
                book.getLanguages(),
                book.getDownloadCount())
            ).collect(Collectors.toList());
    }
}
