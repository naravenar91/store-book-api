package cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.repository;

import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaBookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByName(String name);
}