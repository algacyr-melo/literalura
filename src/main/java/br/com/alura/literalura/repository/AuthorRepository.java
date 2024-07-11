package br.com.alura.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.literalura.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /* NOTE: Maybe could be cleaner using JPQL*/
    List<Author> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(
        Integer birthYear,
        Integer deathYear
    );
}
