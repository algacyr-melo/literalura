package br.com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorDto(
    String name
// Integer birth_year,
// Integer death_year
) {
    @Override
    public final String toString() {
        return name;
    }
}
