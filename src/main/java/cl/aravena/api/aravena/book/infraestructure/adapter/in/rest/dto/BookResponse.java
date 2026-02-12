package cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.dto;

import cl.aravena.api.aravena.book.domain.book.models.Book;

public record BookResponse(
        Long id,
        String name,
        String description,
        Integer pages,
        String authorName,
        String categoryName
) {
    public static BookResponse fromDomain(Book book) {
        return new BookResponse(
                book.id(),
                book.name().value(),
                book.description(),
                book.pages(),
                book.author() != null ? book.author().name().value() : null,
                book.category() != null ? book.category().name().value() : null
        );
    }
}