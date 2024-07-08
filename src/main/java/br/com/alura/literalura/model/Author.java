package br.com.alura.literalura.model;

import java.util.List;

import br.com.alura.literalura.dto.AuthorDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Author
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
        name = "book_author",
        joinColumns = @JoinColumn(name = "author_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    // Constructors
    public Author() {}

    public Author(AuthorDto authorDto)
    {
        this.name = authorDto.name();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
