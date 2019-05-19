package by.gp.clinic.search;

import by.gp.clinic.enumerated.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PatientSearchRequest extends PageableSearchRequest {

    private String name;

    private String lastName;

    private Gender gender;
}
