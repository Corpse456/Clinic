package by.gp.clinic.exception;

public class DoctorExistsException extends EntityExistsException {

    public DoctorExistsException(final String name, final String lastName) {
        super("Doctor " + name + " " + lastName);
    }
}
