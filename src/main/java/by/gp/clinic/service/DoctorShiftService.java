package by.gp.clinic.service;

import by.gp.clinic.converter.DoctorShiftDboDtoConverter;
import by.gp.clinic.converter.ShiftTimingDboDtoConverter;
import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.DoctorShiftDbo;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.dto.ShiftTimingDto;
import by.gp.clinic.enums.ShiftOrder;
import by.gp.clinic.enums.Speciality;
import by.gp.clinic.repository.DoctorShiftRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.next;

@Service
public class DoctorShiftService extends AbstractService<DoctorShiftDbo, DoctorShiftDto> {

    private final DoctorShiftRepository repository;

    private final ShiftTimingDboDtoConverter shiftTimingConverter;

    private final SpecialDoctorShiftService specialDoctorShiftService;
    private final DoctorService doctorService;

    public DoctorShiftService(final DoctorShiftDboDtoConverter doctorShiftConverter,
                              final DoctorShiftRepository repository,
                              final ShiftTimingDboDtoConverter shiftTimingConverter,
                              final SpecialDoctorShiftService specialDoctorShiftService,
                              final DoctorService doctorService) {
        super(doctorShiftConverter, repository);
        this.repository = repository;
        this.shiftTimingConverter = shiftTimingConverter;
        this.specialDoctorShiftService = specialDoctorShiftService;
        this.doctorService = doctorService;
    }

    public Map<LocalDate, ShiftTimingDto> getByDoctorId(final Long id) {
        final List<DoctorShiftDbo> allByDoctorId = repository.getAllByDoctorId(id);
        final Map<DayOfWeek, ShiftTimingDbo> specialShifts
            = specialDoctorShiftService.getSpecialShifts(id, doctorService.getSpeciality(id));

        return allByDoctorId.stream().collect(Collectors.toMap(DoctorShiftDbo::getDate, d -> shiftTimingConverter
            .convertToDto(specialShifts.getOrDefault(d.getDate().getDayOfWeek(), d.getShiftTiming()))));
    }

    public void createTimeTableForWeekAfterNextWeek(final DoctorDbo doctor) {
        final LocalDate thisMonday = LocalDate.now().with(next(MONDAY));
        final ShiftTimingDbo shiftTiming = getShiftByDoctorIDAndDate(doctor.getId(), thisMonday.with(next(FRIDAY)));
        createTimeTableForNextWeek(doctor, thisMonday, shiftTiming.getOppositeShift());
    }

    @Transactional(rollbackOn = Exception.class)
    public void createTimeTableForNextWeek(final DoctorDbo doctor,
                                           final LocalDate from,
                                           ShiftTimingDbo preferredTiming) {
        final Map<DayOfWeek, ShiftTimingDbo> specialShifts
            = specialDoctorShiftService.getSpecialShifts(doctor.getId(), doctor.getSpeciality());
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