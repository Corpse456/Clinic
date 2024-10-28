package by.gp.clinic.mock;

import by.gp.clinic.dbo.SpecialDoctorShiftDbo;
import by.gp.clinic.dto.SpecialDoctorShiftDto;

import java.time.DayOfWeek;

public class SpecialDoctorShiftMock {

    private static final DayOfWeek DAY = DayOfWeek.MONDAY;
    private static final long DOCTOR_ID = 1L;

    public static SpecialDoctorShiftDto getSpecialDoctorShiftDoctorIdDtoMock() {
        final var specialShift = getSpecialDoctorShiftDto();
        specialShift.setDoctorId(DOCTOR_ID);
        return specialShift;
    }

    public static SpecialDoctorShiftDto getSpecialDoctorShiftSpecialityDtoMock() {
        final var specialShift = getSpecialDoctorShiftDto();
        specialShift.setSpecialityId(SpecialityMock.getSpecialityDtoMock().getId());
        return specialShift;
    }

    private static SpecialDoctorShiftDto getSpecialDoctorShiftDto() {
        final var specialShift = new SpecialDoctorShiftDto();
        specialShift.setShiftTiming(ShiftTimingMock.getNotExistsShiftTimingDtoMock());
        specialShift.setWeekDay(DAY);
        return specialShift;
    }

    public static SpecialDoctorShiftDbo getSpecialDoctorShiftDoctorIdDboMock() {
        final var specialShift = getSpecialDoctorShiftDbo();
        specialShift.setDoctor(DoctorMock.getDoctorDboMock());
        return specialShift;
    }

    public static SpecialDoctorShiftDbo getSpecialDoctorShiftSpecialityDboMock() {
        final var specialShift = getSpecialDoctorShiftDbo();
        specialShift.setSpeciality(SpecialityMock.getSpecialityDboMock());
        return specialShift;
    }

    private static SpecialDoctorShiftDbo getSpecialDoctorShiftDbo() {
        final var specialShift = new SpecialDoctorShiftDbo();
        specialShift.setShiftTiming(ShiftTimingMock.getShiftTimingDboMock());
        specialShift.setWeekDay(DAY);
        return specialShift;
    }
}
