package by.gp.clinic.service;

import by.gp.clinic.converter.DoctorShiftDboDtoConverter;
import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.DoctorShiftDbo;
import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.repository.DoctorShiftRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Service
public class DoctorShiftService extends AbstractService<DoctorShiftDbo, DoctorShiftDto> {

    public DoctorShiftService(final DoctorShiftDboDtoConverter converter,
                              final DoctorShiftRepository repository) {
        super(converter, repository);
    }

    public void addNewDoctorToTimeTable(final DoctorDbo doctor) {
        final DoctorShiftDbo doctorShift = new DoctorShiftDbo();
        doctorShift.setDate(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
        doctorShift.setDoctor(doctor);
//        doctorShift.setShiftTiming();
    }
}
