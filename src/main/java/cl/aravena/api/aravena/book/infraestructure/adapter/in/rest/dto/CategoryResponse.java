package cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.dto;

import cl.aravena.api.aravena.book.domain.book.models.Category;

public record CategoryResponse(
        Long id,
        String name
) {
    public static CategoryResponse fromDomain(Category category) {
        return new CategoryResponse(
                category.id(),
                category.name().value()
        );
    }
}