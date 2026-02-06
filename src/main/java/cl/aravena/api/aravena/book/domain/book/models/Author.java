package cl.aravena.api.aravena.book.domain.book.models;

import cl.aravena.api.aravena.book.domain.common.exception.ValidationException;
import cl.aravena.api.aravena.book.domain.common.valueobject.Name;

public record Author(Long id,
                     Name name) {
    public Author {
        if (name == null) {
            throw new ValidationException("Author name cannot be null");
        }
    }
}
