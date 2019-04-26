package by.gp.clinic.exception;

import org.springframework.http.HttpStatus;

public class PatientNotExistsException extends BusinessLogicException {

    public PatientNotExistsException(final Long id) {
        super(HttpStatus.BAD_REQUEST, "Patient with id = " + id + " are not exists");
    }
}
