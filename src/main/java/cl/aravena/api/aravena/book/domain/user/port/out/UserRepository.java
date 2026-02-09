package cl.aravena.api.aravena.book.domain.user.port.out;

import cl.aravena.api.aravena.book.domain.user.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    List<User> getAll();
    Optional<User> findByUserName(String userName);
}
