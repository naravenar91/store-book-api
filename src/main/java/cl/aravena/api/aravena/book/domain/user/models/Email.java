package cl.aravena.api.aravena.book.domain.user.models;

import cl.aravena.api.aravena.book.domain.common.exception.EmailException;
import cl.aravena.api.aravena.book.domain.common.exception.ValidationException;

public record Email(String value) {
    public Email {
        if (value == null || !value.contains("@")) {
            throw new EmailException("\n" +
                    "The email format is invalid");
        }
    }
}