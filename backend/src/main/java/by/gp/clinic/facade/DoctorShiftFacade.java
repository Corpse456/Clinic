package by.gp.clinic.facade;

import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.dto.PageDto;
import by.gp.clinic.dto.ShiftTimingDto;
import by.gp.clinic.dto.SpecialDoctorShiftDto;
import by.gp.clinic.search.DoctorShiftSearchRequest;
import by.gp.clinic.service.DoctorShiftService;
import by.gp.clinic.service.SpecialDoctorShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorShiftFacade {

    private final SpecialDoctorShiftService specialDoctorShiftService;
    private final DoctorShiftService doctorShiftService;

    public Map<LocalDate, ShiftTimingDto> getDoctorShift(final Long id) {
        final var searchRequest = new DoctorShiftSearchRequest();
        searchRequest.setDoctorId(id);

        return doctorShiftService.search(searchRequest).getElements().stream()
            .collect(Collectors.toMap(DoctorShiftDto::getDate,
                                      DoctorShiftDto::getShiftTiming));
    }

    public void postShiftForDate(final DoctorShiftDto doctorShift) {
        doctorShiftService.postShiftForDate(doctorShift);
    }

    public void postSpecialShiftForDate(final SpecialDoctorShiftDto specialDoctorShift) {
        specialDoctorShiftService.post(specialDoctorShift);
    }

    public PageDto<DoctorShiftDto> getDoctorShifts(final DoctorShiftSearchRequest searchRequest) {
        return doctorShiftService.search(searchRequest);
    }
}
