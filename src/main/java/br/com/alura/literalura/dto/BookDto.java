package br.com.alura.literalura.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDto
(
    String title,
    List<AuthorDto> authors,
    String[] languages,
    Long download_count
) {}
