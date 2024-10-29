package by.gp.clinic.service;

import by.gp.clinic.mapper.DoctorShiftDboDtoMapper;
import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.DoctorShiftDbo;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.enumerated.ShiftOrder;
import by.gp.clinic.factory.predicateFactory.DoctorShiftPredicateFactory;
import by.gp.clinic.repository.DoctorShiftRepository;
import by.gp.clinic.search.DoctorShiftSearchRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.next;

@Service
public class DoctorShiftService
    extends AbstractSearchService<DoctorShiftDbo, DoctorShiftDto, DoctorShiftSearchRequest> {

    private final DoctorShiftRepository repository;
    private final DoctorShiftDboDtoMapper doctorShiftConverter;

    public DoctorShiftService(final DoctorShiftPredicateFactory predicateFactory,
                              final DoctorShiftDboDtoMapper doctorShiftConverter,
                              final DoctorShiftRepository repository) {
        super(predicateFactory, doctorShiftConverter, repository);
        this.repository = repository;
        this.doctorShiftConverter = doctorShiftConverter;
    }

    public List<DoctorShiftDbo> getByDoctorId(final Long id) {
        return repository.getAllByDoctorId(id);
    }

    @Transactional
    public void createTimeTableForWeekAfterNextWeek(final DoctorDbo doctor,
                                                    final Map<DayOfWeek, ShiftTimingDbo> specialShifts) {
        final var thisMonday = LocalDate.now().with(next(MONDAY));
        final var shiftTiming = getShiftByDoctorIDAndDate(doctor.getId(), thisMonday.with(next(FRIDAY)));
        createTimeTableForNextWeek(doctor, thisMonday, shiftTiming.getOppositeShift(), specialShifts);
    }

    @Transactional
    public void createTimeTableForNextWeek(final DoctorDbo doctor,
                                           final LocalDate from,
                                           ShiftTimingDbo preferredTiming,
                                           final Map<DayOfWeek, ShiftTimingDbo> specialShifts) {
        var day = from.with(next(MONDAY));

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
        final var doctorShift = new DoctorShiftDbo();
        doctorShift.setDate(day);
        doctorShift.setDoctor(doctor);
        doctorShift.setShiftTiming(shiftTiming);
        save(doctorShift);
    }

    public List<ShiftOrder> getShiftsOrderForDay(final LocalDate date, final Long specialityId) {
        return repository.findShiftOrdersByDateAndSpeciality(date, specialityId);
    }

    @Transactional
    public void postShiftForDate(final DoctorShiftDto doctorShift) {
        final var doctorShiftDbo = doctorShiftConverter.mapToDbo(doctorShift);
        final var savedShift =
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
