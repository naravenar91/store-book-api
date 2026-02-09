package cl.aravena.api.aravena.book.domain.user.models;

import cl.aravena.api.aravena.book.domain.common.valueobject.Name;

import java.util.List;

public record User(Long id,
                   String email,
                   String password,
                   Name name,
                   List<String> roles) {
}
