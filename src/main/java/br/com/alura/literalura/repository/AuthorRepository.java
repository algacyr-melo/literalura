package br.com.alura.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.literalura.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {}
