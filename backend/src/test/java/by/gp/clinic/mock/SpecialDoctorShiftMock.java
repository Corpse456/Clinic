package by.gp.clinic.mock;

import by.gp.clinic.dbo.SpecialDoctorShiftDbo;
import by.gp.clinic.dto.SpecialDoctorShiftDto;
import by.gp.clinic.enumerated.Speciality;

import java.time.DayOfWeek;

public class SpecialDoctorShiftMock {

    private static final DayOfWeek DAY = DayOfWeek.MONDAY;
    private static final long DOCTOR_ID = 1L;
    private static final Speciality SPECIALITY = Speciality.ENDOCRINOLOGIST;

    public static SpecialDoctorShiftDto getSpecialDoctorShiftDoctorIdDtoMock() {
        final SpecialDoctorShiftDto specialShift = getSpecialDoctorShiftDto();
        specialShift.setDoctorId(DOCTOR_ID);
        return specialShift;
    }

    public static SpecialDoctorShiftDto getSpecialDoctorShiftSpecialityDtoMock() {
        final SpecialDoctorShiftDto specialShift = getSpecialDoctorShiftDto();
        specialShift.setSpeciality(SPECIALITY);
        return specialShift;
    }

    private static SpecialDoctorShiftDto getSpecialDoctorShiftDto() {
        final SpecialDoctorShiftDto specialShift = new SpecialDoctorShiftDto();
        specialShift.setShiftTiming(ShiftTimingMock.getNotExistsShiftTimingDtoMock());
        specialShift.setDay(DAY);
        return specialShift;
    }

    public static SpecialDoctorShiftDbo getSpecialDoctorShiftDoctorIdDboMock() {
        final SpecialDoctorShiftDbo specialShift = getSpecialDoctorShiftDbo();
        specialShift.setDoctor(DoctorMock.getDoctorDboMock());
        return specialShift;
    }

    public static SpecialDoctorShiftDbo getSpecialDoctorShiftSpecialityDboMock() {
        final SpecialDoctorShiftDbo specialShift = getSpecialDoctorShiftDbo();
        specialShift.setSpeciality(SPECIALITY);
        return specialShift;
    }

    private static SpecialDoctorShiftDbo getSpecialDoctorShiftDbo() {
        final SpecialDoctorShiftDbo specialShift = new SpecialDoctorShiftDbo();
        specialShift.setShiftTiming(ShiftTimingMock.getShiftTimingDboMock());
        specialShift.setDay(DAY);
        return specialShift;
    }
}
