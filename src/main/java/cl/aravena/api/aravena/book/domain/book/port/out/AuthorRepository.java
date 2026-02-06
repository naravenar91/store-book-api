package cl.aravena.api.aravena.book.domain.book.port.out;

import cl.aravena.api.aravena.book.domain.book.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author create(Author author);
    Author update(Author author);
    void deleteById(Long id);
    Optional<Author> findById(Long id);
    Optional<Author> findByName(String name);
    List<Author> findAll();
}
