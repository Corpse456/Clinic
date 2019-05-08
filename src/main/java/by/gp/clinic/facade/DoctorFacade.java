package by.gp.clinic.facade;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.DoctorDto;
import by.gp.clinic.enums.ShiftOrder;
import by.gp.clinic.exception.DoctorExistsException;
import by.gp.clinic.exception.DoctorNotExistsException;
import by.gp.clinic.exception.ShiftTimingNotExistsException;
import by.gp.clinic.service.DoctorService;
import by.gp.clinic.service.DoctorShiftService;
import by.gp.clinic.service.ShiftTimingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.next;

@Service
@RequiredArgsConstructor
public class DoctorFacade {

    private final DoctorService doctorService;
    private final DoctorShiftService doctorShiftService;
    private final ShiftTimingService shiftTimingService;

    @Transactional(rollbackOn = Exception.class)
    public Long hireDoctor(final DoctorDto doctor) throws DoctorExistsException, ShiftTimingNotExistsException {
        if (doctorService.isExistsByNameAndLastName(doctor.getName(), doctor.getLastName())) {
            throw new DoctorExistsException(doctor.getName(), doctor.getLastName());
        }
        final DoctorDbo savedDoctor = doctorService.post(doctor);
        addNewDoctorToTimeTable(savedDoctor);
        return savedDoctor.getId();
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

    public List<DoctorDto> findAll() {
        return doctorService.findAll();
    }

    public void addNewDoctorToTimeTable(final DoctorDbo doctor) throws ShiftTimingNotExistsException {
        final LocalDate day = LocalDate.now();
        doctorShiftService.createTimeTableForNextWeek(doctor, day, getPreferredTiming(doctor, day.with(next(MONDAY))));
        doctorShiftService.createTimeTableForWeekAfterNextWeek(doctor);
    }

    private ShiftTimingDbo getPreferredTiming(final DoctorDbo doctor, final LocalDate day)
        throws ShiftTimingNotExistsException {
        final List<ShiftOrder> shiftsForDay = doctorShiftService.getShiftsOrderForDay(day, doctor.getSpeciality());
        return shiftTimingService.getPreferredShift(shiftsForDay);
    }
}
