package cl.aravena.api.aravena.book.domain.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ErrorResponse(String message,
                            String error,
                            int status,
                            @JsonFormat(shape = JsonFormat.Shape.STRING,
                            pattern = "yyy-MM-dd HH:mm:ss")
                            LocalDateTime timestamp) {
}
