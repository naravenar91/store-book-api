package cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.repository;

import cl.aravena.api.aravena.book.domain.book.models.Book;
import cl.aravena.api.aravena.book.domain.book.port.out.BookRepository;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.BookEntity;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.mapper.BookMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SpringDataBookRepository implements BookRepository {

    private final JpaBookRepository jpaBookRepository;

    public SpringDataBookRepository(JpaBookRepository jpaBookRepository) {
        this.jpaBookRepository = jpaBookRepository;
    }

    @Override
    public Book save(Book book) {
        BookEntity entity = BookMapper.toEntity(book);
        BookEntity saved = jpaBookRepository.save(entity);
        return BookMapper.toDomain(saved);
    }

    @Override
    public Book update(Book book) {
        BookEntity entity = BookMapper.toEntity(book);
        BookEntity saved = jpaBookRepository.save(entity);
        return BookMapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaBookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAll() {
        return jpaBookRepository.findAll()
                .stream()
                .map(BookMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return jpaBookRepository.findById(id).map(BookMapper::toDomain);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return jpaBookRepository.findByName(name).map(BookMapper::toDomain);
    }
}
