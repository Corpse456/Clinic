package by.gp.clinic.repository;

import by.gp.clinic.dbo.SpecialDoctorShiftDbo;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface SpecialDoctorShiftRepository extends CustomRepository<SpecialDoctorShiftDbo, Long> {

    List<SpecialDoctorShiftDbo> findAllByDoctorIdOrSpecialityId(final Long id, final Long specialityId);
}
