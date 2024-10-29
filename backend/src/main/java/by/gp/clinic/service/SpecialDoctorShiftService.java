package by.gp.clinic.service;

import by.gp.clinic.mapper.SpecialDoctorShiftDboDtoMapper;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dbo.SpecialDoctorShiftDbo;
import by.gp.clinic.dto.SpecialDoctorShiftDto;
import by.gp.clinic.repository.SpecialDoctorShiftRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpecialDoctorShiftService extends AbstractService<SpecialDoctorShiftDbo, SpecialDoctorShiftDto> {

    private final SpecialDoctorShiftRepository repository;

    public SpecialDoctorShiftService(final SpecialDoctorShiftDboDtoMapper mapper,
                                     final SpecialDoctorShiftRepository repository) {
        super(mapper, repository);
        this.repository = repository;
    }

    public Map<DayOfWeek, ShiftTimingDbo> getSpecialShifts(final Long id, final Long specialityId) {
        return repository.findAllByDoctorIdOrSpecialityId(id, specialityId)
            .stream()
            .collect(Collectors.toMap(SpecialDoctorShiftDbo::getWeekDay, SpecialDoctorShiftDbo::getShiftTiming));
    }
}
