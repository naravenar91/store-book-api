package cl.aravena.api.aravena.book.domain.common.exception;

public class NotFoundException extends DomainException {
    private final String name;
    private final Object resourceId;

    public NotFoundException(String name, Object resourceId) {
        super(String.format("%s not found with id: %s", name, resourceId));
        this.name = name;
        this.resourceId = resourceId;
    }
    public String getName() {
        return name;
    }

    public Object getResourceId() {
        return resourceId;
    }

}
