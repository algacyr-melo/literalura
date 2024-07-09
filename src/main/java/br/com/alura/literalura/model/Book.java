package br.com.alura.literalura.model;
import java.util.List;

import br.com.alura.literalura.dto.BookDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Book
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToMany
    private List<Author> authors;

    private String[] languages;

    private Long download_count;

    public Book() {}

    public Book(BookDto bookDto, List<Author> authors)
    {
        this.title = bookDto.title();
        this.languages = bookDto.languages();
        this.download_count = bookDto.download_count();

        this.authors = authors;
    }

    @Override
    public String toString()
    {
        return this.title + " | " + authors;
    }

    public Long getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public List<Author> getAuthors()
    {
        return authors;
    }

    public String[] getLanguages()
    {
        return languages;
    }

    public Long getDownloadCount()
    {
        return download_count;
    }
}
