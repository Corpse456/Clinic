package by.gp.clinic.service;

import by.gp.clinic.converter.SpecialDoctorShiftDboDtoConverter;
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

    public SpecialDoctorShiftService(final SpecialDoctorShiftDboDtoConverter converter,
                                     final SpecialDoctorShiftRepository repository) {
        super(converter, repository);
        this.repository = repository;
    }

    public Map<DayOfWeek, ShiftTimingDbo> getSpecialShifts(final Long id, final Long specialityId) {
        return repository.findAllByDoctorIdOrSpecialityId(id, specialityId)
            .stream()
            .collect(Collectors.toMap(SpecialDoctorShiftDbo::getDay, SpecialDoctorShiftDbo::getShiftTiming));
    }
}
