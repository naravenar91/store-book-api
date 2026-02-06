package cl.aravena.api.aravena.book.domain.common.valueobject;

import cl.aravena.api.aravena.book.domain.common.exception.NotFoundException;
import cl.aravena.api.aravena.book.domain.common.exception.ResourceAlreadyExistsException;

import java.util.Optional;
import java.util.function.Supplier;

public final class EntityValidator {
    private EntityValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Validates that an entity does NOT exist by a specific field
     */
    public static <T> void ensureNotExists(
            Supplier<Optional<T>> finder,
            String entityName,
            String fieldName,
            String fieldValue) {

        finder.get().ifPresent(entity -> {
            throw new ResourceAlreadyExistsException(entityName, fieldName, fieldValue);
        });
    }

    /**
     * Validates that an entity EXISTS by ID
     */
    public static <T> void ensureExists(
            Supplier<Optional<T>> finder,
            String entityName,
            Long id) {

        if (finder.get().isEmpty()) {
            throw new NotFoundException(entityName, id);
        }
    }

    /**
     * Gets an entity or throws NotFoundException
     */
    public static <T> T getOrThrow(
            Supplier<Optional<T>> finder,
            String entityName,
            Long id) {

        return finder.get()
                .orElseThrow(() -> new NotFoundException(entityName, id));
    }
}
