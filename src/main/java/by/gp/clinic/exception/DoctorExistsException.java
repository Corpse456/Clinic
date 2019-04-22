package by.gp.clinic.exception;

import org.springframework.http.HttpStatus;

public class DoctorExistsException extends HttpException {

    public DoctorExistsException(final String name, final String lastName) {
        super(HttpStatus.BAD_REQUEST, "Doctor " + name + " " + lastName + " already exists");
    }
}
