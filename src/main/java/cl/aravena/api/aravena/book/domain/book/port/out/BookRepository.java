package cl.aravena.api.aravena.book.domain.book.port.out;

import cl.aravena.api.aravena.book.domain.book.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Book update(Book book);
    void deleteById(Long id);
    Optional<Book> findById(Long id);
    Optional<Book> findByName(String name);
    List<Book> findAll();
}