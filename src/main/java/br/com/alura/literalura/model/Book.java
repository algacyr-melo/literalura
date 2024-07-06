package br.com.alura.literalura.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Book
{
    @Id
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "books")
    private List<Author> authors;
}
