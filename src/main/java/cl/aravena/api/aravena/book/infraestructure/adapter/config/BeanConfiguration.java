package cl.aravena.api.aravena.book.infraestructure.adapter.config;

import cl.aravena.api.aravena.book.application.service.AuthorServices;
import cl.aravena.api.aravena.book.application.service.BookServices;
import cl.aravena.api.aravena.book.application.service.CategoryServices;
import cl.aravena.api.aravena.book.application.service.UserService;
import cl.aravena.api.aravena.book.domain.book.port.out.AuthorRepository;
import cl.aravena.api.aravena.book.domain.book.port.out.BookRepository;
import cl.aravena.api.aravena.book.domain.book.port.out.CategoryRepository;
import cl.aravena.api.aravena.book.domain.user.port.out.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CategoryServices categoryServices(CategoryRepository categoryRepository) {
        return new CategoryServices(categoryRepository);
    }

    @Bean
    public AuthorServices authorServices(AuthorRepository authorRepository) {
        return new AuthorServices(authorRepository);
    }

    @Bean
    public BookServices bookServices(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        return new BookServices(bookRepository, authorRepository, categoryRepository);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}