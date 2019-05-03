package by.gp.clinic.service;

import by.gp.clinic.converter.ShiftTimingDboDtoConverter;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.ShiftTimingDto;
import by.gp.clinic.repository.ShiftTimingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;

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

    @Transactional
    public void saveNew() {
        final ShiftTimingDbo shiftTiming = repository.findByStartTimeAndEndTime(firstShiftsStart, firstShiftsEnd)
            .orElseGet(() -> {
                final ShiftTimingDbo shiftTimingDbo = new ShiftTimingDbo();
                shiftTimingDbo.setStartTime(firstShiftsStart);
                shiftTimingDbo.setEndTime(firstShiftsEnd);
                return save(shiftTimingDbo);
            });
    }
}
