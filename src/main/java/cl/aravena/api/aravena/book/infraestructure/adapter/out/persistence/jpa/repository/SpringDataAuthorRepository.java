package cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.repository;

import cl.aravena.api.aravena.book.domain.book.models.Author;
import cl.aravena.api.aravena.book.domain.book.port.out.AuthorRepository;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.AuthorEntity;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.mapper.AuthorMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SpringDataAuthorRepository implements AuthorRepository {

    private final JpaAuthorRepository jpaAuthorRepository;

    public SpringDataAuthorRepository(JpaAuthorRepository jpaAuthorRepository) {
        this.jpaAuthorRepository = jpaAuthorRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return jpaAuthorRepository.findById(id).map(AuthorMapper::toDomain);
    }

    @Override
    public Author create(Author author) {
        AuthorEntity newAuthor = jpaAuthorRepository.save(AuthorMapper.toEntity(author));
        return AuthorMapper.toDomain(newAuthor);
    }

    @Override
    public Author update(Author author) {
        AuthorEntity authorUpd = jpaAuthorRepository.save(AuthorMapper.toEntity(author));
        return AuthorMapper.toDomain(authorUpd);
    }

    @Override
    public void deleteById(Long id) {
        jpaAuthorRepository.deleteById(id);
    }

    @Override
    public Optional<Author> findByName(String name) {
        return jpaAuthorRepository.findByName(name).map(AuthorMapper::toDomain);
    }

    @Override
    public List<Author> findAll() {
        return jpaAuthorRepository.findAll().stream().map(AuthorMapper::toDomain).toList();
    }
}
