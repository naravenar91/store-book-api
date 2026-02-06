package cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.mapper;

import cl.aravena.api.aravena.book.domain.book.models.Author;
import cl.aravena.api.aravena.book.domain.book.models.Book;
import cl.aravena.api.aravena.book.domain.book.models.Category;
import cl.aravena.api.aravena.book.domain.common.valueobject.Name;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.AuthorEntity;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.BookEntity;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.CategoryEntity;

public class BookMapper {
    public static Book toDomain(BookEntity entity) {
        if (entity == null) return null;

        Author author = (entity.getAuthor() != null)
                ? new Author(entity.getAuthor().getId(), new Name(entity.getAuthor().getName()))
                : null;

        Category category = (entity.getCategory() != null)
                ? new Category(entity.getCategory().getId(), new Name(entity.getCategory().getName()))
                : null;

        return new Book(
                entity.getId(),
                new Name(entity.getName()),
                entity.getDescription(),
                entity.getPages(),
                author,
                category
        );
    }

    public static BookEntity toEntity(Book domain) {
        if (domain == null) return null;
        BookEntity entity = new BookEntity();
        entity.setId(domain.id());
        entity.setName(domain.name().value());
        entity.setDescription(domain.description());
        entity.setPages(domain.pages());

        if (domain.author() != null && domain.author().id() != null) {
            AuthorEntity authorEntity = new AuthorEntity();
            authorEntity.setId(domain.author().id());
            authorEntity.setName(domain.author().name().value());
            entity.setAuthor(authorEntity);
        }

        if (domain.category() != null && domain.category().id() != null) {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(domain.category().id());
            categoryEntity.setName(domain.category().name().value());
            entity.setCategory(categoryEntity);
        }
        return entity;
    }
}