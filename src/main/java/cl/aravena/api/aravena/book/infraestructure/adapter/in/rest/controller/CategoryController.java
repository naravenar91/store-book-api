package cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.controller;

import cl.aravena.api.aravena.book.domain.book.models.Category;
import cl.aravena.api.aravena.book.domain.book.port.in.category.*;
import cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.dto.CategoryRequest;
import cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.dto.CategoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CreateCategoryUseCase create;
    private final UpdateCategoryUseCase update;
    private final DeleteCategoryByIdUseCase delete;
    private final GetCategoryByIdUseCase get;
    private final GetAllCategoriesUseCase getAll;

    public CategoryController(CreateCategoryUseCase create, UpdateCategoryUseCase update, DeleteCategoryByIdUseCase delete, GetCategoryByIdUseCase get, GetAllCategoriesUseCase getAll) {
        this.create = create;
        this.update = update;
        this.delete = delete;
        this.get = get;
        this.getAll = getAll;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
        Category category = create.create(request.toNewDomain());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.id())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(CategoryResponse.fromDomain(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable Long id,
            @RequestBody CategoryRequest request) {

        Category updated = update.update(request.toExistingDomain(id));
        return ResponseEntity.ok(CategoryResponse.fromDomain(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        delete.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        Category category = get.findById(id);

        return ResponseEntity.ok(CategoryResponse.fromDomain(category));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        List<Category> categories = getAll.getAll();
        List<CategoryResponse> response = categories.stream()
                .map(CategoryResponse::fromDomain)
                .toList();

        return ResponseEntity.ok(response);
    }
}
