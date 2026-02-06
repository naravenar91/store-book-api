package cl.aravena.api.aravena.book.domain.book.port.in.author;

import cl.aravena.api.aravena.book.domain.book.models.Author;

public interface CreateAuthorUseCase {
    Author create(Author author);
}
