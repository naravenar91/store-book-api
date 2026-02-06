package cl.aravena.api.aravena.book.domain.book.port.in.book;

import cl.aravena.api.aravena.book.domain.book.models.Book;

import java.util.Optional;

public interface GetBookByIdUseCase {
    Book findById(Long id);
}
