package br.com.alura.literalura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.alura.literalura.model.Author;
import br.com.alura.literalura.repository.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository AuthorRepository;

    public void saveAll(List<Author> authors) {
        try {
            authors.forEach(author -> AuthorRepository.save(author));
        } catch (DataIntegrityViolationException e) {
        }
    }
}
