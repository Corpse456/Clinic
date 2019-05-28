package by.gp.clinic.exception;

public class UserExistsException extends EntityExistsException {

    public UserExistsException(final String alias) {
        super("User " + alias);
    }
}
