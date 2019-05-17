package by.gp.clinic.mock;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.enumerated.Gender;
import net.bytebuddy.utility.RandomString;

import java.time.LocalDate;
import java.util.ArrayList;

public class DoctorMock {

    private static final String DOCTORS_LAST_NAME = "DoctorsLastName";
    private static final String DOCTORS_NAME = "DoctorsName";
    private static final Gender GENDER = Gender.FEMALE;
    private static final LocalDate BIRTH_DATE = LocalDate.now().minusYears(18);

    public static ArrayList<DoctorDto> getListDoctorDtoMock() {
        final ArrayList<DoctorDto> doctors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            doctors.add(getDoctorDtoMock());
        }
        return doctors;
    }

    public static ArrayList<DoctorDbo> getListDoctorDboMock() {
        final ArrayList<DoctorDbo> doctors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            doctors.add(getDoctorDboMock());
        }
        return doctors;
    }

    public static DoctorDto getDoctorDtoMock() {
        final DoctorDto doctor = new DoctorDto();
        doctor.setSpecialityId(SpecialityMock.getSpecialityDtoMock().getId());
        doctor.setBirthDate(BIRTH_DATE);
        doctor.setGender(GENDER);
        doctor.setName(DOCTORS_NAME + new RandomString(3).nextString());
        doctor.setLastName(DOCTORS_LAST_NAME + new RandomString(3).nextString());
        return doctor;
    }

    public static DoctorDbo getDoctorDboMock() {
        final DoctorDbo doctor = new DoctorDbo();
        doctor.setSpeciality(SpecialityMock.getSpecialityDboMock());
        doctor.setBirthDate(BIRTH_DATE);
        doctor.setGender(GENDER);
        doctor.setName(DOCTORS_NAME + new RandomString(3).nextString());
        doctor.setLastName(DOCTORS_LAST_NAME + new RandomString(3).nextString());
        return doctor;
    }
}
