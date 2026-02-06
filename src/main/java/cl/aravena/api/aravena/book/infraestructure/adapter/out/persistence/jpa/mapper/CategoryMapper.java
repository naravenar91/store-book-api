package cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.mapper;

import cl.aravena.api.aravena.book.domain.book.models.Category;
import cl.aravena.api.aravena.book.domain.common.valueobject.Name;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.CategoryEntity;

public class CategoryMapper {
    public static Category toDomain(CategoryEntity entity) {
        if (entity == null) return null;
        return new Category(entity.getId(), new Name(entity.getName()));
    }

    public static CategoryEntity toEntity(Category domain) {
        if (domain == null) return null;
        CategoryEntity entity = new CategoryEntity();
        entity.setId(domain.id());
        entity.setName(domain.name().value());
        return entity;
    }
}