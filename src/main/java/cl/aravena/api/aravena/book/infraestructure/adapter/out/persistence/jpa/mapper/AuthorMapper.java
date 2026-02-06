package cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.mapper;

import cl.aravena.api.aravena.book.domain.book.models.Author;
import cl.aravena.api.aravena.book.domain.common.valueobject.Name;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.AuthorEntity;

public class AuthorMapper {
    public static Author toDomain(AuthorEntity entity) {
        if (entity == null) return null;
        return new Author(entity.getId(), new Name(entity.getName()));
    }

    public static AuthorEntity toEntity(Author domain) {
        if (domain == null) return null;
        AuthorEntity entity = new AuthorEntity();
        entity.setId(domain.id());
        entity.setName(domain.name().value());
        return entity;
    }
}