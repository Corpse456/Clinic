package by.gp.clinic.mock;

import by.gp.clinic.dbo.DoctorShiftDbo;
import by.gp.clinic.dto.DoctorShiftDto;

import java.time.LocalDate;

public class DoctorShiftMock {

    public static DoctorShiftDto getDoctorShiftDtoMock() {
        final DoctorShiftDto doctorShift = getDoctorShiftDto();
        doctorShift.setShiftTiming(ShiftTimingMock.getShiftTimingDtoMock());
        return doctorShift;
    }

    public static DoctorShiftDto getDoctorShiftNotExistsShiftTimingDtoMock() {
        final DoctorShiftDto doctorShift = getDoctorShiftDto();
        doctorShift.setShiftTiming(ShiftTimingMock.getNotExistsShiftTimingDtoMock());
        return doctorShift;
    }

    private static DoctorShiftDto getDoctorShiftDto() {
        final DoctorShiftDto doctorShift = new DoctorShiftDto();
        doctorShift.setDoctorId(1L);
        doctorShift.setDate(getDate());
        return doctorShift;
    }

    private static LocalDate getDate() {
        return LocalDate.now().plusDays(1);
    }

    public static DoctorShiftDbo getDoctorShiftDboMock() {
        final DoctorShiftDbo doctorShift = new DoctorShiftDbo();
        doctorShift.setDoctor(DoctorMock.getDoctorDboMock());
        doctorShift.setShiftTiming(ShiftTimingMock.getShiftTimingDboMock());
        doctorShift.setDate(getDate());
        return doctorShift;
    }
}
