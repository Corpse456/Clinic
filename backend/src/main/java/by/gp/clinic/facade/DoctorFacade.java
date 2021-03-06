package by.gp.clinic.facade;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.dto.PageDto;
import by.gp.clinic.enumerated.ShiftOrder;
import by.gp.clinic.exception.DoctorExistsException;
import by.gp.clinic.exception.DoctorNotExistsException;
import by.gp.clinic.exception.ShiftTimingNotExistsException;
import by.gp.clinic.search.DoctorSearchRequest;
import by.gp.clinic.service.DoctorService;
import by.gp.clinic.service.DoctorShiftService;
import by.gp.clinic.service.ShiftTimingService;
import by.gp.clinic.service.SpecialDoctorShiftService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.next;

@Service
@RequiredArgsConstructor
public class DoctorFacade {

    private final DoctorService doctorService;
    private final DoctorShiftService doctorShiftService;
    private final SpecialDoctorShiftService specialDoctorShiftService;
    private final ShiftTimingService shiftTimingService;

    @Transactional(rollbackOn = Exception.class)
    public DoctorDbo hireDoctor(final DoctorDto doctor) throws DoctorExistsException, ShiftTimingNotExistsException {
        if (doctorService.isExistsByNameAndLastName(doctor.getName(), doctor.getLastName())) {
            throw new DoctorExistsException(doctor.getName(), doctor.getLastName());
        }
        doctor.setSpecialIdentifier(new RandomString(10).nextString());
        final DoctorDbo savedDoctor = doctorService.post(doctor);
        addNewDoctorToTimeTable(savedDoctor);
        return savedDoctor;
    }

    public void fireDoctor(final Long id) throws DoctorNotExistsException {
        checkDoctorExists(id);
        doctorService.delete(id);
    }

    public DoctorDto getDoctor(final Long id) throws DoctorNotExistsException {
        checkDoctorExists(id);
        return doctorService.get(id);
    }

    private void checkDoctorExists(final Long id) throws DoctorNotExistsException {
        if (!doctorService.isExists(id)) {
            throw new DoctorNotExistsException(id);
        }
    }

    public PageDto<DoctorDto> search(final DoctorSearchRequest searchRequest) {
        return doctorService.search(searchRequest);
    }

    private void addNewDoctorToTimeTable(final DoctorDbo doctor) throws ShiftTimingNotExistsException {
        final LocalDate day = LocalDate.now();
        final Map<DayOfWeek, ShiftTimingDbo> specialShifts =
            specialDoctorShiftService.getSpecialShifts(doctor.getId(), doctor.getSpeciality().getId());
        final ShiftTimingDbo preferredTiming = getPreferredTiming(doctor, day.with(next(MONDAY)));

        doctorShiftService.createTimeTableForNextWeek(doctor, day, preferredTiming, specialShifts);
        doctorShiftService.createTimeTableForWeekAfterNextWeek(doctor, specialShifts);
    }

    private ShiftTimingDbo getPreferredTiming(final DoctorDbo doctor, final LocalDate day)
        throws ShiftTimingNotExistsException {
        final List<ShiftOrder> shiftsForDay =
            doctorShiftService.getShiftsOrderForDay(day, doctor.getSpeciality().getId());
        return shiftTimingService.getPreferredShift(shiftsForDay);
    }
}
