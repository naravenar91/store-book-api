package cl.aravena.api.aravena.book.infraestructure.adapter.in.rest.exception;

import cl.aravena.api.aravena.book.domain.common.exception.DomainException;
import cl.aravena.api.aravena.book.domain.common.exception.ErrorResponse;
import cl.aravena.api.aravena.book.domain.common.exception.NotFoundException;
import cl.aravena.api.aravena.book.domain.common.exception.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    //private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private ResponseEntity<ErrorResponse> buildError(String message, String code, HttpStatus status) {
        ErrorResponse error = new ErrorResponse(
                message,
                code,
                status.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        return buildError(ex.getMessage(), "NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DomainException.class, ValidationException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(RuntimeException ex) {
        return buildError(ex.getMessage(), "BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex) {
        //log.error("Data integrity violation", ex);
        return buildError("Violación de integridad de datos", "BAD_REQUEST", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobal(Exception ex) {
        //log.error("Unexpected error", ex);
        return buildError("Ocurrió un error inesperado", "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}