package by.gp.clinic.mock;

import by.gp.clinic.dto.PatientDto;
import by.gp.clinic.enums.Gender;
import net.bytebuddy.utility.RandomString;

import java.time.LocalDate;

public class PatientMock {

    private static final String PATIENT_LAST_NAME = "PatientLastName";
    private static final String PATIENT_NAME = "PatientName";
    private static final Gender GENDER = Gender.MALE;
    private static final LocalDate BIRTH_DATE = LocalDate.now().minusYears(50);

    public static PatientDto getPatientDtoMock() {
        final PatientDto patient = new PatientDto();
        patient.setBirthDate(BIRTH_DATE);
        patient.setGender(GENDER);
        patient.setName(PATIENT_NAME + new RandomString(3).nextString());
        patient.setLastName(PATIENT_LAST_NAME + new RandomString(3).nextString());
        return patient;
    }
}
