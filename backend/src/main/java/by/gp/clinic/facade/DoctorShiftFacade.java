package by.gp.clinic.facade;

import by.gp.clinic.converter.ShiftTimingDboDtoConverter;
import by.gp.clinic.dbo.DoctorShiftDbo;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.dto.ShiftTimingDto;
import by.gp.clinic.dto.SpecialDoctorShiftDto;
import by.gp.clinic.service.DoctorService;
import by.gp.clinic.service.DoctorShiftService;
import by.gp.clinic.service.SpecialDoctorShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorShiftFacade {

    private final SpecialDoctorShiftService specialDoctorShiftService;
    private final DoctorShiftService doctorShiftService;
    private final DoctorService doctorService;

    private final ShiftTimingDboDtoConverter shiftTimingDboDtoConverter;

    public Map<LocalDate, ShiftTimingDto> getDoctorShift(final Long id) {
        final Map<DayOfWeek, ShiftTimingDbo> specialShifts =
            specialDoctorShiftService.getSpecialShifts(id, doctorService.getSpeciality(id));

        return doctorShiftService.getByDoctorId(id).stream()
            .collect(Collectors.toMap(DoctorShiftDbo::getDate, d -> shiftTimingDboDtoConverter
                .convertToDto(specialShifts.getOrDefault(d.getDate().getDayOfWeek(), d.getShiftTiming()))));
    }

    public void postShiftForDate(final DoctorShiftDto doctorShift) {
        doctorShiftService.postShiftForDate(doctorShift);
    }

    public void postSpecialShiftForDate(final SpecialDoctorShiftDto specialDoctorShift) {
        specialDoctorShiftService.post(specialDoctorShift);
    }
}
