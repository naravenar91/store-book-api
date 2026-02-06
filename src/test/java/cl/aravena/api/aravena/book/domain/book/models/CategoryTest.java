package cl.aravena.api.aravena.book.domain.book.models;

import cl.aravena.api.aravena.book.domain.common.exception.ValidationException;
import cl.aravena.api.aravena.book.domain.common.valueobject.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    @Test
    void createValidCategory() {
        // Given
        Long expectedId = 1L;
        Name expectedName = new Name("Anime");

        // When
        Category category = new Category(expectedId, expectedName);

        // Then
        assertEquals(expectedId, category.id());
        assertEquals(expectedName, category.name());
        assertEquals("Anime", category.name().value());
    }

    @Test
    void createCategoryWithNullId() {
        Name name = new Name("Anime");

        Category category = new Category(null, name);

        assertNull(category.id());
        assertEquals(name, category.name());
    }

    @Test
    void notAllowNullName() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> new Category(1L, null));

        assertEquals("Category name cannot be null", exception.getMessage());
    }

    @Test
    void notAllowNullNameEvenWithNullId() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> new Category(null, null));

        assertEquals("Category name cannot be null", exception.getMessage());
    }

    @Test
    void shouldNotAllowNullName() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> new Category(1L, null));

        assertEquals("Category name cannot be null", exception.getMessage());
    }
}