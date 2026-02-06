package cl.aravena.api.aravena.book.domain.book.port.in.category;

import cl.aravena.api.aravena.book.domain.book.models.Category;

public interface UpdateCategoryUseCase {
    Category update(Category category);
}
