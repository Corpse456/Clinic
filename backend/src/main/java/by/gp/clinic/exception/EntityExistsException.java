package by.gp.clinic.exception;

import org.springframework.http.HttpStatus;

public class EntityExistsException extends BusinessLogicException {

    public EntityExistsException(final String name) {
        super(HttpStatus.BAD_REQUEST, name + " already exists");
    }
}
