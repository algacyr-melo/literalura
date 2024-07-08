package br.com.alura.literalura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.alura.literalura.model.Book;
import br.com.alura.literalura.repository.BookRepository;

@Service
public class BookService
{
    @Autowired
    BookRepository bookRepository;

    public void saveBook(Book book)
    {
        try
        {
            bookRepository.save(book);
        }
        catch (DataIntegrityViolationException e) {}
    }
}
