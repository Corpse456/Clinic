package by.gp.clinic.service;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.next;

import by.gp.clinic.converter.DoctorShiftDboDtoConverter;
import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.DoctorShiftDbo;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.enumerated.ShiftOrder;
import by.gp.clinic.factory.predicateFactory.DoctorShiftPredicateFactory;
import by.gp.clinic.repository.DoctorShiftRepository;
import by.gp.clinic.search.DoctorShiftSearchRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DoctorShiftService
    extends AbstractSearchService<DoctorShiftDbo, DoctorShiftDto, DoctorShiftSearchRequest> {

    private final DoctorShiftRepository repository;
    private final DoctorShiftDboDtoConverter doctorShiftConverter;

    public DoctorShiftService(final DoctorShiftPredicateFactory predicateFactory,
                              final DoctorShiftDboDtoConverter doctorShiftConverter,
                              final DoctorShiftRepository repository) {
        super(predicateFactory, doctorShiftConverter, repository);
        this.repository = repository;
        this.doctorShiftConverter = doctorShiftConverter;
    }

    public List<DoctorShiftDbo> getByDoctorId(final Long id) {
        return repository.getAllByDoctorId(id);
    }

    public void createTimeTableForWeekAfterNextWeek(final DoctorDbo doctor,
                                                    final Map<DayOfWeek, ShiftTimingDbo> specialShifts) {
        final LocalDate thisMonday = LocalDate.now().with(next(MONDAY));
        final ShiftTimingDbo shiftTiming = getShiftByDoctorIDAndDate(doctor.getId(), thisMonday.with(next(FRIDAY)));
        createTimeTableForNextWeek(doctor, thisMonday, shiftTiming.getOppositeShift(), specialShifts);
    }

    @Transactional(rollbackOn = Exception.class)
    public void createTimeTableForNextWeek(final DoctorDbo doctor,
                                           final LocalDate from,
                                           ShiftTimingDbo preferredTiming,
                                           final Map<DayOfWeek, ShiftTimingDbo> specialShifts) {
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

    public List<ShiftOrder> getShiftsOrderForDay(final LocalDate date, final Long specialityId) {
        return repository.findShiftOrdersByDateAndSpeciality(date, specialityId);
    }

    public void postShiftForDate(final DoctorShiftDto doctorShift) {
        final DoctorShiftDbo doctorShiftDbo = doctorShiftConverter.convertToDbo(doctorShift);
        final Optional<DoctorShiftDbo> savedShift =
            repository.getByDoctorIdAndDate(doctorShift.getDoctorId(), doctorShift.getDate());
        if (savedShift.isPresent()) {
            savedShift.get().setShiftTiming(doctorShiftDbo.getShiftTiming());
            save(savedShift.get());
        } else {
            save(doctorShiftDbo);
        }
    }

    private ShiftTimingDbo getShiftByDoctorIDAndDate(final Long id, final LocalDate date) {
        return repository.getShiftTimingByDoctorIdAndDate(id, date);
    }

    private boolean isDoctorShiftExists(final Long id, final LocalDate date) {
        return repository.existsByDoctorIdAndDate(id, date);
    }

    public boolean isDoctorWorkingHours(final Long id, final LocalDateTime dateTime) {
        return repository.countByDoctorIdAndDateTime(id, dateTime.toLocalDate(), dateTime.toLocalTime()) > 0;
    }
}
