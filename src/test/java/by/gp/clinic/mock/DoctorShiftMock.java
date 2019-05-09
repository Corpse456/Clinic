package by.gp.clinic.mock;

import by.gp.clinic.dbo.DoctorShiftDbo;
import by.gp.clinic.dto.DoctorShiftDto;

import java.time.LocalDate;

public class DoctorShiftMock {

    public static DoctorShiftDto getDoctorShiftDtoMock() {
        final DoctorShiftDto doctorShift = new DoctorShiftDto();
        doctorShift.setDoctorId(1L);
        doctorShift.setShiftTiming(ShiftTimingMock.getShiftTImingDtoMock());
        doctorShift.setDate(LocalDate.now());
        return doctorShift;
    }

    public static DoctorShiftDbo getDoctorShiftDboMock() {
        final DoctorShiftDbo doctorShift = new DoctorShiftDbo();
        doctorShift.setDoctor(DoctorMock.getDoctorDboMock());
        doctorShift.setShiftTiming(ShiftTimingMock.getShiftTImingDboMock());
        doctorShift.setDate(LocalDate.now());
        return doctorShift;
    }
}
