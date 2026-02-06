package cl.aravena.api.aravena.book.domain.book.models;

import cl.aravena.api.aravena.book.domain.common.exception.ValidationException;
import cl.aravena.api.aravena.book.domain.common.valueobject.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    @Test
    void createValidAuthor() {
        // Given
        Long expectedId = 1L;
        Name expectedName = new Name("nico");

        // When
        Author author = new Author(expectedId, expectedName);

        // Then
        assertEquals(expectedId, author.id());
        assertEquals(expectedName, author.name());
        assertEquals("nico", author.name().value());
    }

    @Test
    void createAuthorWithNullId() {
        Name name = new Name("nico");

        Author author = new Author(null, name);

        assertNull(author.id());
        assertEquals(name, author.name());
    }

    @Test
    void notAllowNullName() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> new Author(1L, null));

        assertEquals("Author name cannot be null", exception.getMessage());
    }

    @Test
    void notAllowNullNameEvenWithNullId() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> new Author(null, null));

        assertEquals("Author name cannot be null", exception.getMessage());
    }

    @Test
    void shouldNotAllowNullName() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> new Author(1L, null));

        assertEquals("Author name cannot be null", exception.getMessage());
    }
}