package by.gp.clinic.repository;

import by.gp.clinic.dbo.ShiftTimingDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ShiftTimingRepository extends JpaRepository<ShiftTimingDbo, Long> {
}