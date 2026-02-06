package cl.aravena.api.aravena.book.domain.book.port.in.book;

import cl.aravena.api.aravena.book.domain.book.models.Book;

public interface CreateBookUseCase {
    Book create(Book book);
}