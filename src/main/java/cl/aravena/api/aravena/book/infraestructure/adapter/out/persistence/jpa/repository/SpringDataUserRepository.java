package cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.repository;

import cl.aravena.api.aravena.book.domain.common.exception.NotFoundException;
import cl.aravena.api.aravena.book.domain.user.models.User;
import cl.aravena.api.aravena.book.domain.user.port.out.UserRepository;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.UserEntity;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.mapper.UserMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SpringDataUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public SpringDataUserRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity newUser = jpaUserRepository.save(UserMapper.toEntity(user));
        return UserMapper.toDomain(newUser);
    }

    @Override
    public List<User> getAll() {
        return jpaUserRepository.findAll().stream().map(UserMapper::toDomain).toList();
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return jpaUserRepository.findByEmail(userName)
                .map(UserMapper::toDomain);
    }
}
