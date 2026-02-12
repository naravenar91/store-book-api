package cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.repository;

import cl.aravena.api.aravena.book.domain.book.models.Book;
import cl.aravena.api.aravena.book.domain.book.port.out.BookRepository;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.BookEntity;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.mapper.BookMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class BookRepositoryTest extends BaseIntegrationTest {

    @Autowired
    private BookRepository bookRepository; // Tu interfaz de JPA

    @Test
    @DisplayName("Debería guardar un libro y recuperarlo por ID")
    void saveAndFindBookTest() {
        // 1. Preparar el dato (Asegúrate de tener un autor y categoría previos en la DB)
        // Nota: Los scripts en src/main/resources/db NO se ejecutan solos aquí,
        // a menos que uses @Sql o configures hibernate ddl-auto: create

        BookEntity book = new BookEntity();
        book.setName("Test Driven Development");
        book.setDescription("Clean code con tests");
        book.setPages(300);
        // Setea los IDs de autor y categoría que existan...

        // 2. Ejecutar acción
        Book savedBook = bookRepository.save(BookMapper.toDomain(book));

        // 3. Verificar resultados
        assertNotNull(savedBook.id());
        assertEquals("Test Driven Development", savedBook.name());
    }
}