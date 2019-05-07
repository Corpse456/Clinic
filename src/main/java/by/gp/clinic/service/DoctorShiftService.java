package by.gp.clinic.service;

import by.gp.clinic.converter.DoctorShiftDboDtoConverter;
import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.DoctorShiftDbo;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.enums.ShiftOrder;
import by.gp.clinic.enums.Speciality;
import by.gp.clinic.repository.DoctorShiftRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.next;

@Service
public class DoctorShiftService extends AbstractService<DoctorShiftDbo, DoctorShiftDto> {

    private final DoctorShiftRepository repository;
    private final DoctorShiftDboDtoConverter converter;

    public DoctorShiftService(final DoctorShiftDboDtoConverter converter,
                              final DoctorShiftRepository repository) {
        super(converter, repository);
        this.repository = repository;
        this.converter = converter;
    }

    public List<DoctorShiftDto> getByDoctorId(final Long id) {
        final List<DoctorShiftDbo> allByDoctorId = repository.getAllByDoctorId(id);
        return converter.convertToDto(allByDoctorId);
    }

    public void createTimeTableForWeekAfterNextWeek(final DoctorDbo doctor,
                                                    final Map<DayOfWeek, ShiftTimingDbo> specialShifts) {
        final LocalDate thisMonday = LocalDate.now().with(next(MONDAY));
        final ShiftTimingDbo shiftTiming = getShiftByDoctorIDAndDate(doctor.getId(), thisMonday.with(next(FRIDAY)));
        createTimeTableForNextWeek(doctor, thisMonday, specialShifts, shiftTiming.getOppositeShift());
    }

    @Transactional(rollbackOn = Exception.class)
    public void createTimeTableForNextWeek(final DoctorDbo doctor,
                                           final LocalDate from,
                                           final Map<DayOfWeek, ShiftTimingDbo> specialShifts,
                                           ShiftTimingDbo preferredTiming) {
        LocalDate day = from.with(next(MONDAY));

        while (day.getDayOfWeek() != DayOfWeek.SATURDAY) {
            if (!isDoctorShiftExists(doctor.getId(), day)) {
                addDoctorShift(doctor, specialShifts.getOrDefault(day.getDayOfWeek(), preferredTiming), day);
            }
            day = day.plusDays(1);
            preferredTiming = preferredTiming.getOppositeShift();
        }
    }

    @Transactional
    public void addDoctorShift(final DoctorDbo doctor, final ShiftTimingDbo shiftTiming, final LocalDate day) {
        final DoctorShiftDbo doctorShift = new DoctorShiftDbo();
        doctorShift.setDate(day);
        doctorShift.setDoctor(doctor);
        doctorShift.setShiftTiming(shiftTiming);
        save(doctorShift);
    }

    public List<ShiftOrder> getShiftsOrderForDay(final LocalDate date, final Speciality speciality) {
        return repository.findShiftOrdersByDateAndSpeciality(date, speciality);
    }

    private ShiftTimingDbo getShiftByDoctorIDAndDate(final Long id, final LocalDate date) {
        return repository.getShiftTimingByDoctorIdAndDate(id, date);
    }

    private boolean isDoctorShiftExists(final Long id, final LocalDate date) {
        return repository.existsByDoctorIdAndDate(id, date);
    }
}