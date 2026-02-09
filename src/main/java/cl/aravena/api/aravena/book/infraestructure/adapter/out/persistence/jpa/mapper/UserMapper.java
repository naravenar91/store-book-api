package cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.mapper;


import cl.aravena.api.aravena.book.domain.common.valueobject.Name;
import cl.aravena.api.aravena.book.domain.user.models.User;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.RoleEntity;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.UserEntity;

public class UserMapper {
    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return new User(entity.getId(), entity.getEmail(), entity.getPassword(), new Name(entity.getName())
        , entity.getRoles().stream()
                .map(RoleEntity::getName) // Extraemos el String "ROLE_ADMIN"
                .toList());
    }

    public static UserEntity toEntity(User domain) {
        if (domain == null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(domain.id());
        entity.setEmail(domain.email());
        entity.setPassword(domain.password());
        entity.setName(domain.name().value());
        return entity;
    }
}
