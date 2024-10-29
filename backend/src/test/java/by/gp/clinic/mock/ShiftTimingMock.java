package by.gp.clinic.mock;

import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.ShiftTimingDto;
import by.gp.clinic.enumerated.ShiftOrder;

import java.time.LocalTime;

public class ShiftTimingMock {

    private static final LocalTime EXISTS_START_TIME = LocalTime.of(8, 0);
    private static final LocalTime EXISTS_END_TIME = LocalTime.of(13, 0);
    private static final LocalTime START_TIME = LocalTime.of(5, 0);
    private static final LocalTime END_TIME = LocalTime.of(14, 0);

    public static ShiftTimingDto getShiftTimingDtoMock() {
        return getShiftTimingDto(EXISTS_START_TIME, EXISTS_END_TIME);
    }

    static ShiftTimingDto getNotExistsShiftTimingDtoMock() {
        return getShiftTimingDto(START_TIME, END_TIME);
    }

    private static ShiftTimingDto getShiftTimingDto(final LocalTime startTime, final LocalTime endTime) {
        final var shiftTiming = new ShiftTimingDto();
        shiftTiming.setId(1L);
        shiftTiming.setStartTime(startTime);
        shiftTiming.setEndTime(endTime);
        shiftTiming.setShiftOrder(ShiftOrder.FIRST);
        return shiftTiming;
    }

    public static ShiftTimingDbo getShiftTimingDboMock() {
        return getShiftTimingDboMock(EXISTS_START_TIME, EXISTS_END_TIME);
    }

    private static ShiftTimingDbo getShiftTimingDboMock(final LocalTime startTime, final LocalTime endTime) {
        final var shiftTiming = new ShiftTimingDbo();
        shiftTiming.setId(1L);
        shiftTiming.setStartTime(startTime);
        shiftTiming.setEndTime(endTime);
        shiftTiming.setShiftOrder(ShiftOrder.FIRST);
        return shiftTiming;
    }
}
