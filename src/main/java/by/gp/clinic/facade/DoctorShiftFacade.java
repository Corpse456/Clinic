package by.gp.clinic.facade;

import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.dto.ShiftTimingDto;
import by.gp.clinic.service.DoctorShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DoctorShiftFacade {

    private final DoctorShiftService doctorShiftService;

    public Map<LocalDate, ShiftTimingDto> getDoctorShift(final Long id) {
        return doctorShiftService.getByDoctorId(id);
    }

    public void postSpecialShiftForDate(final DoctorShiftDto doctorShift) {

    }
}
