package cl.aravena.api.aravena.book.application.service;

import cl.aravena.api.aravena.book.domain.common.exception.NotFoundException;
import cl.aravena.api.aravena.book.domain.user.models.User;
import cl.aravena.api.aravena.book.domain.user.port.in.GetUserByUserNameUseCase;
import cl.aravena.api.aravena.book.domain.user.port.out.UserRepository;

public class UserService implements GetUserByUserNameUseCase {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new NotFoundException("User", userName));
    }
}
