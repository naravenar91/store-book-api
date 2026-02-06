package cl.aravena.api.aravena.book.domain.user.models;

import cl.aravena.api.aravena.book.domain.common.valueobject.Name;

public record User(Long id,
                   Name name,
                   String email,
                   String address) {
}
