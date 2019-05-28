package by.gp.clinic.exception;

public class PatientNotExistsException extends EntityNotExistsException {

    public PatientNotExistsException(final Long id) {
        super("Patient", id);
    }

    public PatientNotExistsException(final String name, final String lastName) {
        super("Patient " + name + " " + lastName);
    }
}
