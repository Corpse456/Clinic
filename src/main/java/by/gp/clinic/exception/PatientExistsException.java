package by.gp.clinic.exception;

import org.springframework.http.HttpStatus;

public class PatientExistsException extends HttpException {

    public PatientExistsException(final String name, final String lastName) {
        super(HttpStatus.BAD_REQUEST, "Patient " + name + " " + lastName + " already exists");
    }
}
