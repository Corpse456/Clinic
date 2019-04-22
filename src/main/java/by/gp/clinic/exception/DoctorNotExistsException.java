package by.gp.clinic.exception;

import org.springframework.http.HttpStatus;

public class DoctorNotExistsException extends HttpException {

    public DoctorNotExistsException(final Long id) {
        super(HttpStatus.BAD_REQUEST, "Doctor with id = " + id + " are not exists");
    }
}
