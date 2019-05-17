package by.gp.clinic.service;

import by.gp.clinic.converter.ShiftTimingDboDtoConverter;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.ShiftTimingDto;
import by.gp.clinic.enumerated.ShiftOrder;
import by.gp.clinic.exception.ShiftTimingNotExistsException;
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

    private ShiftTimingDbo getUsualFirstShiftTiming() throws ShiftTimingNotExistsException {
        return getShiftTimingDbo(firstShiftsStart, firstShiftsEnd);
    }

    private ShiftTimingDbo getUsualSecondShiftTiming() throws ShiftTimingNotExistsException {
        return getShiftTimingDbo(secondShiftsStart, secondShiftsEnd);
    }

    @Transactional
    public ShiftTimingDbo getShiftTimingDbo(final LocalTime startTime,
                                            final LocalTime endTime) throws ShiftTimingNotExistsException {
        return repository.getByStartTimeAndEndTime(startTime, endTime)
            .orElseThrow(() -> new ShiftTimingNotExistsException(startTime, endTime));
    }

    @Transactional
    public ShiftTimingDbo getShiftTimingDboOrCreate(final LocalTime startTime,
                                                    final LocalTime endTime,
                                                    final ShiftOrder shiftOrder) {
        return repository.getByStartTimeAndEndTime(startTime, endTime)
            .orElseGet(() -> {
                final ShiftTimingDbo shiftTimingDbo = new ShiftTimingDbo();
                shiftTimingDbo.setStartTime(startTime);
                shiftTimingDbo.setEndTime(endTime);
                shiftTimingDbo.setShiftOrder(shiftOrder);
                final ShiftTimingDbo saved = save(shiftTimingDbo);
                saved.setOppositeShift(saved);
                return saved;
            });
    }

    public ShiftTimingDbo getPreferredShift(final List<ShiftOrder> shiftsForDay) throws ShiftTimingNotExistsException {
        int firstShifts = 0, secondShifts = 0;
        for (final ShiftOrder shiftOrder : shiftsForDay) {
            if (ShiftOrder.FIRST == shiftOrder) {
                firstShifts++;
            }
            if (ShiftOrder.SECOND == shiftOrder) {
                secondShifts++;
            }
        }
        return firstShifts <= secondShifts ? getUsualFirstShiftTiming() : getUsualSecondShiftTiming();
    }
}
