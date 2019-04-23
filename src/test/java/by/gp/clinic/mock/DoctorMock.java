package by.gp.clinic.mock;

import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.enums.Gender;
import by.gp.clinic.enums.Specialty;

import java.time.LocalDate;

public class DoctorMock {

    private static final String DOCTORS_LAST_NAME = "DoctorsLastName";
    private static final String DOCTORS_NAME = "DoctorsName";
    private static final Gender GENDER = Gender.FEMALE;
    private static final LocalDate BIRTH_DATE = LocalDate.now().minusYears(18);
    private static final Specialty SPECIALTY = Specialty.CARDIOLOGIST;

    public static DoctorDto getDoctorDtoMock() {
        final DoctorDto doctor = new DoctorDto();
        doctor.setSpecialty(SPECIALTY);
        doctor.setBirthDate(BIRTH_DATE);
        doctor.setGender(GENDER);
        doctor.setName(DOCTORS_NAME);
        doctor.setLastName(DOCTORS_LAST_NAME);
        return doctor;
    }
}
