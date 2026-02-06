package cl.aravena.api.aravena.book.domain.book.port.in.book;

import cl.aravena.api.aravena.book.domain.book.models.Book;

import java.util.List;

public interface GetAllBooksUseCase {
    List<Book> getAll();
}
