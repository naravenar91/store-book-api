package cl.aravena.api.aravena.book.domain.book.port.out;

import cl.aravena.api.aravena.book.domain.book.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category create(Category category);
    Category update(Category category);
    void deleteById(Long id);
    Optional<Category> findById(Long id);
    Optional<Category> findByName(String name);
    List<Category> findAll();
}
