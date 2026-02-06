package cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.repository;

import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaAuthorRepository extends JpaRepository<AuthorEntity, Long> {
    Optional<AuthorEntity> findByName(String name);
}
