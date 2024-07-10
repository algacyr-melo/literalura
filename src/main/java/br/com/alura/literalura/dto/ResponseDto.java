package br.com.alura.literalura.dto;

import java.util.List;

public record ResponseDto(
    Long count,
    String next,
    String previous,
    List<BookDto> results
) {
}
