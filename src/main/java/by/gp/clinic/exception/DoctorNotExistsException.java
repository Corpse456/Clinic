package by.gp.clinic.exception;

public class DoctorNotExistsException extends EntityNotExistsException {

    public DoctorNotExistsException(final Long id) {
        super("Doctor", id);
    }
}
