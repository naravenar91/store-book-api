package cl.aravena.api.aravena.book.domain.book.port.in.author;

import cl.aravena.api.aravena.book.domain.book.models.Author;

import java.util.Optional;

public interface GetAuthorByIdUseCase {
    Optional<Author> getByID(Long id);
}
