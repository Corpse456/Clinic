package by.gp.clinic.mock;

import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.enums.Gender;
import by.gp.clinic.enums.Speciality;

import java.time.LocalDate;

public class DoctorShiftMock {

    private static final String DOCTORS_LAST_NAME = "DoctorsLastName";
    private static final String DOCTORS_NAME = "DoctorsName";
    private static final Gender GENDER = Gender.FEMALE;
    private static final LocalDate BIRTH_DATE = LocalDate.now().minusYears(18);
    private static final Speciality SPECIALITY = Speciality.CARDIOLOGIST;

    public static DoctorShiftDto getDoctorDtoMock() {
        final DoctorShiftDto doctorShift = new DoctorShiftDto();
        doctorShift.setDoctor(DoctorMock.getDoctorDtoMock());
        doctorShift.setShiftTiming(ShiftTimingMock.getDoctorDtoMock());
        doctorShift.setDate(LocalDate.now());
        return doctorShift;
    }
}
