package cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.dto;

import cl.aravena.api.aravena.book.domain.book.models.Author;

public record AuthorResponse(
        Long id,
        String name
) {
    public static AuthorResponse fromDomain(Author author) {
        return new AuthorResponse(
                author.id(),
                author.name().value()
        );
    }
}