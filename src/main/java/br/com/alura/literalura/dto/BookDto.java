package br.com.alura.literalura.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDto
(
    Long id,
    String title,
    List<AuthorDto> authors
) {}
