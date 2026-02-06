package cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.controller;

import cl.aravena.api.aravena.book.domain.book.models.Book;
import cl.aravena.api.aravena.book.domain.book.port.in.book.*;
import cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.dto.BookRequest;
import cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.dto.BookResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final CreateBookUseCase create;
    private final UpdateBookUseCase update;
    private final DeleteBookByIdUseCase delete;
    private final GetBookByIdUseCase get;
    private final GetAllBooksUseCase getAll;

    public BookController(CreateBookUseCase create, UpdateBookUseCase update, DeleteBookByIdUseCase delete, GetBookByIdUseCase get, GetAllBooksUseCase getAll) {
        this.create = create;
        this.update = update;
        this.delete = delete;
        this.get = get;
        this.getAll = getAll;
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody BookRequest request) {
        Book book = create.create(request.toNewDomain());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(book.id())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(BookResponse.fromDomain(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(
            @PathVariable Long id,
            @RequestBody BookRequest request) {

        Book updated = update.update(request.toExistingDomain(id));

        return ResponseEntity.ok(BookResponse.fromDomain(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        delete.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(BookResponse.fromDomain(get.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAll() {
        List<BookResponse> response = getAll.getAll()
                .stream()
                .map(BookResponse::fromDomain)
                .toList();

        return ResponseEntity.ok(response);
    }
}