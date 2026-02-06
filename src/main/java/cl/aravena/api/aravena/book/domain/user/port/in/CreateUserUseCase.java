package cl.aravena.api.aravena.book.domain.user.port.in;

import cl.aravena.api.aravena.book.domain.user.models.User;

public interface CreateUserUseCase {
    User createUser(User user);
}
