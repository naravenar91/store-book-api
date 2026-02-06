package cl.aravena.api.aravena.book.application.service;

import cl.aravena.api.aravena.book.domain.book.models.Author;
import cl.aravena.api.aravena.book.domain.book.models.Book;
import cl.aravena.api.aravena.book.domain.book.models.Category;
import cl.aravena.api.aravena.book.domain.book.port.in.book.*;
import cl.aravena.api.aravena.book.domain.book.port.out.AuthorRepository;
import cl.aravena.api.aravena.book.domain.book.port.out.BookRepository;
import cl.aravena.api.aravena.book.domain.book.port.out.CategoryRepository;
import cl.aravena.api.aravena.book.domain.common.exception.NotFoundException;
import cl.aravena.api.aravena.book.domain.common.exception.ResourceAlreadyExistsException;

import java.util.List;

public class BookServices implements CreateBookUseCase, UpdateBookUseCase, DeleteBookByIdUseCase, GetBookByIdUseCase, GetAllBooksUseCase {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookServices(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Book create(Book book) {
        if(bookRepository.findByName(book.name().value()).isPresent()){
            throw new ResourceAlreadyExistsException("Book","name", book.name().value());
        }

        Author author = authorRepository.findById(book.author().id())
                .orElseThrow(() -> new NotFoundException("Author", book.author().id()));

        Category category = categoryRepository.findById(book.category().id())
                .orElseThrow(() -> new NotFoundException("Category", book.category().id()));

        Book bookWithFullData = book.withFullRelations(author, category);

        return bookRepository.save(bookWithFullData);
    }

    @Override
    public Book update(Book book) {
        if(bookRepository.findById(book.id()).isEmpty()){
            throw new NotFoundException("Book", book.id());
        }

        Author author = authorRepository.findById(book.author().id())
                .orElseThrow(() -> new NotFoundException("Author", book.author().id()));

        Category category = categoryRepository.findById(book.category().id())
                .orElseThrow(() -> new NotFoundException("Category", book.category().id()));

        Book bookUpd = book.withFullRelations(author, category);

        return bookRepository.update(bookUpd);
    }

    @Override
    public void deleteById(Long id) {
        if(bookRepository.findById(id).isEmpty()){
            throw new NotFoundException("Book", id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book", id));
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }
}
