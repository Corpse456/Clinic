package by.gp.clinic.exception;

public class PatientExistsException extends EntityExistsException {

    public PatientExistsException(final String name, final String lastName) {
        super("Patient " + name + " " + lastName);
    }
}
