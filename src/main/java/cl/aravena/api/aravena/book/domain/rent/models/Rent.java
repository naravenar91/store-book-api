package cl.aravena.api.aravena.book.domain.rent.models;

import cl.aravena.api.aravena.book.domain.book.models.Book;
import cl.aravena.api.aravena.book.domain.user.models.User;

import java.time.LocalDateTime;

public record Rent(Long idRent,
                   Book book,
                   User user,
                   LocalDateTime transactionDate,
                   LocalDateTime startDate,
                   LocalDateTime returnDate,
                   Integer dailyValue,
                   Integer totalValue
                   ) {
}