package by.gp.clinic.exception;

public class PatientNotExistsException extends EntityNotExistsException {

    public PatientNotExistsException(final Long id) {
        super("Patient", id);
    }
}
