package cl.aravena.api.aravena.book.domain.common.valueobject;

import cl.aravena.api.aravena.book.domain.common.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @Test
    void shouldCreateValidName() {
        Name name = new Name("nico");

        assertEquals("nico", name.value());
    }

    @Test
    void shouldNotAllowNullValue() {
        assertThrows(ValidationException.class, () -> new Name(null));
    }

    @Test
    void shouldNotAllowBlankValue() {
        assertThrows(ValidationException.class, () -> new Name(""));
        assertThrows(ValidationException.class, () -> new Name("   "));
    }
}