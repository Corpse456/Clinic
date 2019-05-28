package by.gp.clinic.exception;

public class DoctorNotExistsException extends EntityNotExistsException {

    public DoctorNotExistsException(final Long id) {
        super("Doctor", id);
    }

    public DoctorNotExistsException(final String name, final String lastName, final String specialIdentifier) {
        super("Doctor " + name + " " + lastName + " with id = " + specialIdentifier);
    }
}
