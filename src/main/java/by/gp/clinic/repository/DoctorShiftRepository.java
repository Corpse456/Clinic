package by.gp.clinic.repository;

import by.gp.clinic.dbo.DoctorShiftDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface DoctorShiftRepository extends JpaRepository<DoctorShiftDbo, Long> {
}