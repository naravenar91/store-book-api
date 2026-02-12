package cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.dto;

import cl.aravena.api.aravena.book.domain.book.models.Category;
import cl.aravena.api.aravena.book.domain.common.valueobject.Name;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request by create a Category")
public record CategoryRequest(
        @Schema(description = "Category Name", example = "Romance")
        String name){

    @JsonIgnore
    public Category toNewDomain() {
        return new Category(null, new Name(this.name));
    }

    public Category toExistingDomain(Long id) {
        return new Category(id, new Name(this.name));
    }
}
