package br.com.alura.literalura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.alura.literalura.model.Author;
import br.com.alura.literalura.repository.AuthorRepository;

@Service
public class AuthorService
{
    @Autowired
    AuthorRepository AuthorRepository;

    public void saveAuthor(Author author)
    {
        try
        {
            AuthorRepository.save(author);
        }
        catch (DataIntegrityViolationException e) {}
    }
}
