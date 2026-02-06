package cl.aravena.api.aravena.book.application.service;

import cl.aravena.api.aravena.book.domain.book.models.Category;
import cl.aravena.api.aravena.book.domain.book.port.in.category.*;
import cl.aravena.api.aravena.book.domain.book.port.out.CategoryRepository;
import cl.aravena.api.aravena.book.domain.common.exception.NotFoundException;
import cl.aravena.api.aravena.book.domain.common.valueobject.EntityValidator;

import java.util.List;

public class CategoryServices implements CreateCategoryUseCase, UpdateCategoryUseCase, DeleteCategoryByIdUseCase, GetCategoryByIdUseCase, GetAllCategoriesUseCase {

    private final CategoryRepository categoryRepository;

    public CategoryServices(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(Category category) {
        EntityValidator.ensureNotExists(
                () -> categoryRepository.findByName(category.name().value()),
                "Category", "name", category.name().value()
        );
        return categoryRepository.create(category);
    }

    @Override
    public Category update(Category category) {
        if(categoryRepository.findById(category.id()).isEmpty()){
            throw new NotFoundException("Category", category.id());
        }
        return categoryRepository.update(category);
    }

    @Override
    public void deleteById(Long id) {
        if(categoryRepository.findById(id).isEmpty()){
            throw new NotFoundException("Category", id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category", id));
    }

    @Override
    public List<Category> getAll() {
        List<Category> getAll = categoryRepository.findAll();
        return getAll;
    }
}
