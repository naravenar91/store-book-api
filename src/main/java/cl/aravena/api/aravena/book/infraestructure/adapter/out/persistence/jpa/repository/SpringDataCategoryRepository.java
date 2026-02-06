package cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.repository;

import cl.aravena.api.aravena.book.domain.book.models.Category;
import cl.aravena.api.aravena.book.domain.book.port.out.CategoryRepository;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.entity.CategoryEntity;
import cl.aravena.api.aravena.book.infraestructure.adapter.out.persistence.jpa.mapper.CategoryMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class SpringDataCategoryRepository implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;

    public SpringDataCategoryRepository(JpaCategoryRepository jpaCategoryRepository) {
        this.jpaCategoryRepository = jpaCategoryRepository;
    }
    @Override
    public Optional<Category> findById(Long id) {
        return jpaCategoryRepository.findById(id).map(CategoryMapper::toDomain);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return jpaCategoryRepository.findByName(name).map(CategoryMapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return jpaCategoryRepository.findAll().stream().map(CategoryMapper::toDomain).toList();
    }

    @Override
    public Category create(Category category) {
        CategoryEntity newCategory =  jpaCategoryRepository.save(CategoryMapper.toEntity(category));
        return CategoryMapper.toDomain(newCategory);
    }

    @Override
    public Category update(Category category) {
        CategoryEntity newCategory =  jpaCategoryRepository.save(CategoryMapper.toEntity(category));
        return CategoryMapper.toDomain(newCategory);
    }

    @Override
    public void deleteById(Long id) {
        jpaCategoryRepository.deleteById(id);
    }
}
