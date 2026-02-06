package cl.aravena.api.aravena.book.domain.book.models;

import cl.aravena.api.aravena.book.domain.common.exception.ValidationException;
import cl.aravena.api.aravena.book.domain.common.valueobject.Name;

public record Category(Long id,
                       Name name) {
    public Category {
        if (name == null) {
            throw new ValidationException("Category name cannot be null");
        }
    }
}
