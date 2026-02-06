package cl.aravena.api.aravena.book.domain.common.valueobject;

import cl.aravena.api.aravena.book.domain.common.exception.ValidationException;
//import com.fasterxml.jackson.annotation.JsonValue;

public record Name(String value) {
    public Name {
        if (value == null || value.isBlank()) {
            throw new ValidationException("The name is required");
        }
    }

    /*
    @JsonValue // Esto le dice a Jackson: "Usa solo el String 'value' para el JSON"
    public String getValue() {
        return value;
    }
    */
}