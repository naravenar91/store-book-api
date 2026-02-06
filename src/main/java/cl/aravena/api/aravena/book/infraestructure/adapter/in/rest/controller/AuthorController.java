package cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.controller;

import cl.aravena.api.aravena.book.domain.book.models.Author;
import cl.aravena.api.aravena.book.domain.book.port.in.author.*;
import cl.aravena.api.aravena.book.domain.common.exception.NotFoundException;
import cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.dto.AuthorRequest;
import cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.dto.AuthorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private final CreateAuthorUseCase createAuthorUseCase;
    private final UpdateAuthorUseCase updateAuthorUseCase;
    private final DeleteAuthorUseCase deleteAuthorUseCase;
    private final GetAuthorByIdUseCase getAuthorByIdUseCase;
    private final GetAllAuthorsUseCase getAllAuthorsUseCase;

    public AuthorController(CreateAuthorUseCase createAuthorUseCase, UpdateAuthorUseCase updateAuthorUseCase, DeleteAuthorUseCase deleteAuthorUseCase, GetAuthorByIdUseCase getAuthorByIdUseCase, GetAllAuthorsUseCase getAllAuthorsUseCase) {
        this.createAuthorUseCase = createAuthorUseCase;
        this.updateAuthorUseCase = updateAuthorUseCase;
        this.deleteAuthorUseCase = deleteAuthorUseCase;
        this.getAuthorByIdUseCase = getAuthorByIdUseCase;
        this.getAllAuthorsUseCase = getAllAuthorsUseCase;
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> create(@RequestBody AuthorRequest request) {
        Author author = createAuthorUseCase.create(request.toNewDomain());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.id())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(AuthorResponse.fromDomain(author));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(
            @PathVariable Long id,
            @RequestBody AuthorRequest request) {

        Author updated = updateAuthorUseCase.update(request.toExistingDomain(id));
        return ResponseEntity.ok(AuthorResponse.fromDomain(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteAuthorUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAll(@PathVariable Long id) {
        Optional<Author> authorOptional = getAuthorByIdUseCase.getByID(id);

        return authorOptional
                .map(author -> ResponseEntity.ok(AuthorResponse.fromDomain(author)))
                .orElseThrow(() -> new NotFoundException("Author", id));
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAll() {
        List<Author> authors = getAllAuthorsUseCase.getAll();
        List<AuthorResponse> response = authors.stream()
                .map(AuthorResponse::fromDomain)
                .toList();

        return ResponseEntity.ok(response);
    }
}
