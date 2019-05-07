package by.gp.clinic.service;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.ShiftTimingDbo;
import by.gp.clinic.dbo.SpecialDoctorShiftDbo;
import by.gp.clinic.repository.SpecialDoctorShiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SpecialDoctorShiftService {

    private final SpecialDoctorShiftRepository repository;

    public Map<DayOfWeek, ShiftTimingDbo> getSpecialShifts(final DoctorDbo doctor) {
        return repository.findAllByDoctorIdOrSpeciality(doctor.getId(), doctor.getSpeciality())
            .stream()
            .collect(Collectors.toMap(SpecialDoctorShiftDbo::getDay, SpecialDoctorShiftDbo::getShiftTiming));
    }
}
