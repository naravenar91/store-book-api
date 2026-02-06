package cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.dto;

import cl.aravena.api.aravena.book.domain.book.models.Author;
import cl.aravena.api.aravena.book.domain.common.valueobject.Name;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request by create a Author")
public record AuthorRequest(
    @Schema(description = "Name Author", example = "Juan")
    String name){

    @JsonIgnore
    public Author toNewDomain() {
        return new Author(null, new Name(this.name));
    }

    public Author toExistingDomain(Long id) {
        return new Author(id, new Name(this.name));
    }
}
