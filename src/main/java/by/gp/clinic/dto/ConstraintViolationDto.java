package by.gp.clinic.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConstraintViolationDto implements Serializable {
    private String field;
    private String message;
}
