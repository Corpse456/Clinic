package by.gp.clinic.repository;

import by.gp.clinic.dbo.SpecialityDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<SpecialityDbo, Long> {
}
