package cl.aravena.api.aravena.book.application.service;

import cl.aravena.api.aravena.book.domain.book.models.Author;
import cl.aravena.api.aravena.book.domain.book.port.in.author.*;
import cl.aravena.api.aravena.book.domain.book.port.out.AuthorRepository;
import cl.aravena.api.aravena.book.domain.common.exception.NotFoundException;
import cl.aravena.api.aravena.book.domain.common.exception.ResourceAlreadyExistsException;

import java.util.List;
import java.util.Optional;

public class AuthorServices implements CreateAuthorUseCase, UpdateAuthorUseCase, DeleteAuthorUseCase, GetAuthorByIdUseCase, GetAllAuthorsUseCase {
    private final AuthorRepository authorRepository;

    public AuthorServices(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author create(Author author) {
        if(authorRepository.findByName(author.name().value()).isPresent()){
            throw new ResourceAlreadyExistsException("Author","name", author.name().value());
        }
        return authorRepository.create(author);
    }

    @Override
    public Author update(Author author) {
        if(authorRepository.findById(author.id()).isEmpty()){
            throw new NotFoundException("Author", author.id());
        }
        return authorRepository.update(author);
    }

    @Override
    public void deleteById(Long id) {
        if(authorRepository.findById(id).isEmpty()){
            throw new NotFoundException("Author", id);
        }
        authorRepository.deleteById(id);
    }

    @Override
    public Optional<Author> getByID(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }
}
