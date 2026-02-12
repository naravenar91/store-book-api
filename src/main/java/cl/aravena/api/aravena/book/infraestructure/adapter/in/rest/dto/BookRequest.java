package cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.dto;

import cl.aravena.api.aravena.book.domain.book.models.Author;
import cl.aravena.api.aravena.book.domain.book.models.Book;
import cl.aravena.api.aravena.book.domain.book.models.Category;
import cl.aravena.api.aravena.book.domain.common.valueobject.Name;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request by create a book")
public record BookRequest(
        @Schema(description = "Name book", example = "One Punch Man")
        String name,
        @Schema(description = "Description Book", example = "Bald man with cape")
        String description,
        @Schema(description = "Number Page", example = "123")
        Integer pages,
        @Schema(description = "ID Author", example = "1")
        Long authorId,
        @Schema(description = "ID Category", example = "2")
        Long categoryId
) {
    @JsonIgnore
    public Book toNewDomain() {
        return new Book(null,
                new Name(this.name),
                this.description,
                this.pages,
                new Author(authorId, null),
                new Category(categoryId, null));
    }

    public Book toExistingDomain(Long id) {
    return new Book(id,
            new Name(this.name),
            this.description,
            this.pages,
            new Author(authorId, null),
            new Category(categoryId, null));
    }
}