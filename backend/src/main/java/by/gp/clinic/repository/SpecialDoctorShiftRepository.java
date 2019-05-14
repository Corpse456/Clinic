package by.gp.clinic.repository;

import by.gp.clinic.dbo.SpecialDoctorShiftDbo;
import by.gp.clinic.enums.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface SpecialDoctorShiftRepository extends JpaRepository<SpecialDoctorShiftDbo, Long> {

    List<SpecialDoctorShiftDbo> findAllByDoctorIdOrSpeciality (final Long id, final Speciality speciality);
}