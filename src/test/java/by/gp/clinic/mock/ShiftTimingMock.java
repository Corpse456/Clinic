package by.gp.clinic.mock;

import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.ShiftTimingDto;
import by.gp.clinic.enums.Gender;
import by.gp.clinic.enums.ShiftOrder;
import by.gp.clinic.enums.Speciality;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShiftTimingMock {

    private static final LocalTime END_TIME = LocalTime.of(13, 0);
    private static final LocalTime START_TIME = LocalTime.of(8, 0);

    public static ShiftTimingDto getShiftTImingDtoMock() {
        final ShiftTimingDto shiftTiming = new ShiftTimingDto();
        shiftTiming.setId(1L);
        shiftTiming.setStartTime(START_TIME);
        shiftTiming.setEndTime(END_TIME);
        shiftTiming.setShiftOrder(ShiftOrder.FIRST);
        return shiftTiming;
    }

    public static ShiftTimingDbo getShiftTImingDboMock() {
        final ShiftTimingDbo shiftTiming = new ShiftTimingDbo();
        shiftTiming.setId(1L);
        shiftTiming.setStartTime(START_TIME);
        shiftTiming.setEndTime(END_TIME);
        shiftTiming.setShiftOrder(ShiftOrder.FIRST);
        return shiftTiming;
    }
}
