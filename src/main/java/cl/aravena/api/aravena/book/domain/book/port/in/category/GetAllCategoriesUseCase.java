package cl.aravena.api.aravena.book.domain.book.port.in.category;

import cl.aravena.api.aravena.book.domain.book.models.Category;

import java.util.List;

public interface GetAllCategoriesUseCase {
    List<Category> getAll();
}
