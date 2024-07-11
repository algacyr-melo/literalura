package br.com.alura.literalura.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.alura.literalura.dto.AuthorDto;
import br.com.alura.literalura.model.Author;
import br.com.alura.literalura.repository.AuthorRepository;
import jakarta.transaction.Transactional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public void saveAll(List<Author> authors) {
        try {
            authors.forEach(author -> authorRepository.save(author));
        } catch (DataIntegrityViolationException e) {
        }
    }

    @Transactional
    public List<AuthorDto> findAll() {
        List<Author> authors = authorRepository.findAll();

        return authors.stream()
            .map(author -> new AuthorDto(
                author.getName(),
                author.getBirthYear(),
                author.getDeathYear())
            ).collect(Collectors.toList());
    }

    @Transactional
    public List<AuthorDto> findAuthorsAliveAt(Integer year) {
        List<Author> authors = authorRepository
            .findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year, year);

        return authors.stream()
            .map(author -> new AuthorDto(
                author.getName(),
                author.getBirthYear(),
                author.getDeathYear())
            ).collect(Collectors.toList());
    }
}
