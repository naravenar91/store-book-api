package cl.aravena.api.aravena.book.domain.book.models;

import cl.aravena.api.aravena.book.domain.common.valueobject.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Name validName;
    private Author validAuthor;
    private Category validCategory;

    @BeforeEach
    void setUp() {
        validName = new Name("Clean Code");
        validAuthor = new Author(1L, new Name("Robert C. Martin"));
        validCategory = new Category(1L, new Name("Programming"));
    }

    @Test
    @DisplayName("Should create a valid book with all fields")
    void shouldCreateValidBookWithAllFields() {
        Book book = new Book(1L, validName, "A handbook of agile software craftsmanship",
                464, validAuthor, validCategory);

        assertEquals(1L, book.id());
        assertEquals(validName, book.name());
        assertEquals("A handbook of agile software craftsmanship", book.description());
        assertEquals(464, book.pages());
        assertEquals(validAuthor, book.author());
        assertEquals(validCategory, book.category());
    }

    @Test
    @DisplayName("Should create a valid book with null id (new book)")
    void shouldCreateValidBookWithNullId() {
        Book book = new Book(null, validName, "Description", 100, validAuthor, validCategory);

        assertNull(book.id());
        assertEquals(validName, book.name());
    }

    @Test
    @DisplayName("Should create a valid book with null pages")
    void shouldCreateValidBookWithNullPages() {
        Book book = new Book(1L, validName, "Description", null, validAuthor, validCategory);

        assertNull(book.pages());
    }

    @Test
    @DisplayName("Should create a valid book with null author")
    void shouldCreateValidBookWithNullAuthor() {
        Book book = new Book(1L, validName, "Description", 100, null, validCategory);

        assertNull(book.author());
    }

    @Test
    @DisplayName("Should create a valid book with null category")
    void shouldCreateValidBookWithNullCategory() {
        Book book = new Book(1L, validName, "Description", 100, validAuthor, null);

        assertNull(book.category());
    }

    // Validation Tests

    @Test
    @DisplayName("Should not allow null name")
    void shouldNotAllowNullName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Book(1L, null, "Description", 100, validAuthor, validCategory));

        assertEquals("Name cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should not allow null description")
    void shouldNotAllowNullDescription() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Book(1L, validName, null, 100, validAuthor, validCategory));

        assertEquals("Description cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should not allow zero pages")
    void shouldNotAllowZeroPages() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Book(1L, validName, "Description", 0, validAuthor, validCategory));

        assertEquals("Pages must be greater than 0", exception.getMessage());
    }

    @Test
    @DisplayName("Should not allow negative pages")
    void shouldNotAllowNegativePages() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Book(1L, validName, "Description", -10, validAuthor, validCategory));

        assertEquals("Pages must be greater than 0", exception.getMessage());
    }

    // With methods tests

    @Test
    @DisplayName("Should create new book with different id using withId")
    void shouldCreateNewBookWithDifferentId() {
        Book originalBook = new Book(1L, validName, "Description", 100, validAuthor, validCategory);

        Book updatedBook = originalBook.withId(2L);

        assertEquals(2L, updatedBook.id());
        assertEquals(originalBook.name(), updatedBook.name());
        assertEquals(originalBook.description(), updatedBook.description());
        assertEquals(originalBook.pages(), updatedBook.pages());
        assertEquals(originalBook.author(), updatedBook.author());
        assertEquals(originalBook.category(), updatedBook.category());
        assertNotSame(originalBook, updatedBook);
    }

    @Test
    @DisplayName("Should create new book with different name using withName")
    void shouldCreateNewBookWithDifferentName() {
        Book originalBook = new Book(1L, validName, "Description", 100, validAuthor, validCategory);
        Name newName = new Name("The Pragmatic Programmer");

        Book updatedBook = originalBook.withName(newName);

        assertEquals(newName, updatedBook.name());
        assertEquals(originalBook.id(), updatedBook.id());
        assertNotSame(originalBook, updatedBook);
    }

    @Test
    @DisplayName("Should create new book with different description using withDescription")
    void shouldCreateNewBookWithDifferentDescription() {
        Book originalBook = new Book(1L, validName, "Description", 100, validAuthor, validCategory);
        String newDescription = "New description";

        Book updatedBook = originalBook.withDescription(newDescription);

        assertEquals(newDescription, updatedBook.description());
        assertEquals(originalBook.id(), updatedBook.id());
        assertNotSame(originalBook, updatedBook);
    }

    @Test
    @DisplayName("Should create new book with different pages using withPages")
    void shouldCreateNewBookWithDifferentPages() {
        Book originalBook = new Book(1L, validName, "Description", 100, validAuthor, validCategory);

        Book updatedBook = originalBook.withPages(200);

        assertEquals(200, updatedBook.pages());
        assertEquals(originalBook.id(), updatedBook.id());
        assertNotSame(originalBook, updatedBook);
    }

    @Test
    @DisplayName("Should create new book with different author using withAuthor")
    void shouldCreateNewBookWithDifferentAuthor() {
        Book originalBook = new Book(1L, validName, "Description", 100, validAuthor, validCategory);
        Author newAuthor = new Author(2L, new Name("Martin Fowler"));

        Book updatedBook = originalBook.withAuthor(newAuthor);

        assertEquals(newAuthor, updatedBook.author());
        assertEquals(originalBook.id(), updatedBook.id());
        assertNotSame(originalBook, updatedBook);
    }

    @Test
    @DisplayName("Should create new book with different category using withCategory")
    void shouldCreateNewBookWithDifferentCategory() {
        Book originalBook = new Book(1L, validName, "Description", 100, validAuthor, validCategory);
        Category newCategory = new Category(2L, new Name("Software Engineering"));

        Book updatedBook = originalBook.withCategory(newCategory);

        assertEquals(newCategory, updatedBook.category());
        assertEquals(originalBook.id(), updatedBook.id());
        assertNotSame(originalBook, updatedBook);
    }

    @Test
    @DisplayName("Should create new book with full relations using withFullRelations")
    void shouldCreateNewBookWithFullRelations() {
        Book originalBook = new Book(1L, validName, "Description", 100, validAuthor, validCategory);
        Author newAuthor = new Author(2L, new Name("Kent Beck"));
        Category newCategory = new Category(2L, new Name("Testing"));

        Book updatedBook = originalBook.withFullRelations(newAuthor, newCategory);

        assertEquals(newAuthor, updatedBook.author());
        assertEquals(newCategory, updatedBook.category());
        assertEquals(originalBook.id(), updatedBook.id());
        assertEquals(originalBook.name(), updatedBook.name());
        assertEquals(originalBook.description(), updatedBook.description());
        assertEquals(originalBook.pages(), updatedBook.pages());
        assertNotSame(originalBook, updatedBook);
    }

    @Test
    @DisplayName("Should maintain immutability - original book unchanged after withId")
    void shouldMaintainImmutabilityAfterWithId() {
        Book originalBook = new Book(1L, validName, "Description", 100, validAuthor, validCategory);
        Long originalId = originalBook.id();

        originalBook.withId(999L);

        assertEquals(originalId, originalBook.id());
    }

    @Test
    @DisplayName("Should validate when using withName with null")
    void shouldValidateWhenUsingWithNameWithNull() {
        Book originalBook = new Book(1L, validName, "Description", 100, validAuthor, validCategory);

        assertThrows(IllegalArgumentException.class, () -> originalBook.withName(null));
    }

    @Test
    @DisplayName("Should validate when using withDescription with null")
    void shouldValidateWhenUsingWithDescriptionWithNull() {
        Book originalBook = new Book(1L, validName, "Description", 100, validAuthor, validCategory);

        assertThrows(IllegalArgumentException.class, () -> originalBook.withDescription(null));
    }

    @Test
    @DisplayName("Should validate when using withPages with invalid value")
    void shouldValidateWhenUsingWithPagesWithInvalidValue() {
        Book originalBook = new Book(1L, validName, "Description", 100, validAuthor, validCategory);

        assertThrows(IllegalArgumentException.class, () -> originalBook.withPages(0));
        assertThrows(IllegalArgumentException.class, () -> originalBook.withPages(-5));
    }
}