package by.gp.clinic.exception;

public class UserNotExistsException extends EntityNotExistsException {

    public UserNotExistsException(final Long id) {
        super("User with id = " + id);
    }
}
