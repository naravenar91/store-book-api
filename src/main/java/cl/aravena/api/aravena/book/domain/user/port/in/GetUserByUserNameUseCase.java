package cl.aravena.api.aravena.book.domain.user.port.in;

import cl.aravena.api.aravena.book.domain.user.models.User;

import java.util.Optional;

public interface GetUserByUserNameUseCase {
    User findByUserName(String userName);
}
