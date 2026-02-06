package cl.aravena.api.aravena.book.domain.book.port.in.author;

import cl.aravena.api.aravena.book.domain.book.models.Author;

import java.util.List;

public interface GetAllAuthorsUseCase {
    List<Author> getAll();
}
