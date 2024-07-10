package br.com.alura.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.literalura.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b from Book b LEFT JOIN FETCH b.authors")
    List<Book> getBooksAll();
}
