package by.gp.clinic.exception;

public class DoctorExistsException extends Exception {

    public DoctorExistsException(final String name, final String lastName) {
        super("Doctor " + name + " " + lastName + " already exists");
    }
}
