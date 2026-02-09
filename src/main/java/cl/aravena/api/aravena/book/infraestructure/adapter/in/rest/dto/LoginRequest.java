package cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequest(
        @Schema(description = "User name of user registered (email)", example = "nicolas.aravena25@gmail.com")
        String userName,
        @Schema(description = "Password access", example = "nicolas1234")
        String password) {
}
