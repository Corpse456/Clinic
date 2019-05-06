package by.gp.clinic.service;

import by.gp.clinic.converter.DoctorShiftDboDtoConverter;
import by.gp.clinic.dbo.DoctorShiftDbo;
import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.enums.Speciality;
import by.gp.clinic.repository.DoctorShiftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class DoctorShiftService extends AbstractService<DoctorShiftDbo, DoctorShiftDto> {

    private final DoctorShiftRepository repository;

    public DoctorShiftService(final DoctorShiftDboDtoConverter converter,
                              final DoctorShiftRepository repository) {
        super(converter, repository);
        this.repository = repository;
    }

    public List<LocalTime> getStartTimeForDay(final LocalDate date, final Speciality speciality) {
        return repository.findStartTimeByDateAndSpeciality(date, speciality);
    }
}
