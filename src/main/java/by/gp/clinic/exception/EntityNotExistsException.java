package by.gp.clinic.exception;

import org.springframework.http.HttpStatus;

public class EntityNotExistsException extends BusinessLogicException {

    public EntityNotExistsException(final String name, final Long id) {
        super(HttpStatus.NOT_FOUND, name + " with id = " + id + " are not exists");
    }

    public EntityNotExistsException(final String name) {
        super(HttpStatus.BAD_REQUEST, name + " are not exists");
    }
}
