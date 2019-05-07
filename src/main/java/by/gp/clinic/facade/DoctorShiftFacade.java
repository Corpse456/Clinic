package by.gp.clinic.facade;

import by.gp.clinic.dto.DoctorShiftDto;
import by.gp.clinic.service.DoctorShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorShiftFacade {

    private final DoctorShiftService doctorShiftService;

    public List<DoctorShiftDto> getDoctorShift(final Long id) {
        return doctorShiftService.getByDoctorId(id);
    }
}
