package by.gp.clinic.service;

import by.gp.clinic.dbo.SpecialDoctorShiftDbo;
import by.gp.clinic.enums.Speciality;
import by.gp.clinic.repository.SpecialDoctorShiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpecialDoctorShiftService {

    private final SpecialDoctorShiftRepository repository;

    public List<SpecialDoctorShiftDbo> findSpecialShifts(final Long id, final Speciality speciality) {
        return repository.findAllByDoctorIdOrSpeciality(id, speciality);
    }
}
