package by.gp.clinic.service;

import by.gp.clinic.converter.ShiftTimingDboDtoConverter;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.ShiftTimingDto;
import by.gp.clinic.enums.ShiftOrder;
import by.gp.clinic.repository.ShiftTimingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

@Service
public class ShiftTimingService extends AbstractService<ShiftTimingDbo, ShiftTimingDto> {

    @Value("#{T(java.time.LocalTime).parse(\"${clinic.first.shifts.start}\")}")
    private LocalTime firstShiftsStart;
    @Value("#{T(java.time.LocalTime).parse(\"${clinic.first.shifts.end}\")}")
    private LocalTime firstShiftsEnd;
    @Value("#{T(java.time.LocalTime).parse(\"${clinic.second.shifts.start}\")}")
    private LocalTime secondShiftsStart;
    @Value("#{T(java.time.LocalTime).parse(\"${clinic.second.shifts.end}\")}")
    private LocalTime secondShiftsEnd;

    private final ShiftTimingRepository repository;

    public ShiftTimingService(final ShiftTimingDboDtoConverter converter,
                              final ShiftTimingRepository repository) {
        super(converter, repository);
        this.repository = repository;
    }

    public ShiftTimingDbo getUsualFirstShiftTiming() {
        return getShiftTimingDbo(firstShiftsStart, firstShiftsEnd, ShiftOrder.FIRST);
    }

    public ShiftTimingDbo getUsualSecondShiftTiming() {
        return getShiftTimingDbo(secondShiftsStart, secondShiftsEnd, ShiftOrder.SECOND);
    }

    @Transactional
    public ShiftTimingDbo getShiftTimingDbo(final LocalTime startTime,
                                            final LocalTime endTime,
                                            final ShiftOrder shiftOrder) {
        return repository.findByStartTimeAndEndTime(startTime, endTime)
            .orElseGet(() -> {
                final ShiftTimingDbo shiftTimingDbo = new ShiftTimingDbo();
                shiftTimingDbo.setStartTime(startTime);
                shiftTimingDbo.setEndTime(endTime);
                shiftTimingDbo.setShiftOrder(shiftOrder);
                return save(shiftTimingDbo);
            });
    }

    public ShiftTimingDbo getPreferredShift(final List<LocalTime> shiftsForDay) {
        int firstShifts = 0, secondShifts = 0;
        for (final LocalTime time : shiftsForDay) {
            if (firstShiftsStart.equals(time)) {
                firstShifts++;
            }
            if (secondShiftsStart.equals(time)) {
                secondShifts++;
            }
        }
        return firstShifts < secondShifts ? getUsualFirstShiftTiming() : getUsualSecondShiftTiming();
    }
}
