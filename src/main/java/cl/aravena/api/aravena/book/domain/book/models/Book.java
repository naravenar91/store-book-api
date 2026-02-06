package cl.aravena.api.aravena.book.domain.book.models;

import cl.aravena.api.aravena.book.domain.common.valueobject.Name;

public record Book(Long id,
                   Name name,
                   String description,
                   Integer pages,
                   Author author,
                   Category category) {

    public Book withId(Long id) {
        return new Book(id, this.name, this.description, this.pages, this.author, this.category);
    }

    public Book withName(Name name) {
        return new Book(this.id, name, this.description, this.pages, this.author, this.category);
    }

    public Book withDescription(String description) {
        return new Book(this.id, this.name, description, this.pages, this.author, this.category);
    }

    public Book withPages(Integer pages) {
        return new Book(this.id, this.name, this.description, pages, this.author, this.category);
    }

    public Book withAuthor(Author author) {
        return new Book(this.id, this.name, this.description, this.pages, author, this.category);
    }

    public Book withCategory(Category category) {
        return new Book(this.id, this.name, this.description, this.pages, this.author, category);
    }

    public Book withFullRelations(Author fullAuthor, Category fullCategory) {
        return new Book(
                this.id,
                this.name,
                this.description,
                this.pages,
                fullAuthor,
                fullCategory
        );
    }

    public Book {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (pages != null && pages <= 0) {
            throw new IllegalArgumentException("Pages must be greater than 0");
        }
    }
}