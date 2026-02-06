package cl.aravena.api.aravena.book.domain.user.port.out;

import cl.aravena.api.aravena.book.domain.user.models.User;

import java.util.List;

public interface UserRepository {
    User save(User user);
    List<User> getAll();
}
