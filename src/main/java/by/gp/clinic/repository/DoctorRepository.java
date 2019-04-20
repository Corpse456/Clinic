package by.gp.clinic.repository;

import by.gp.clinic.dbo.DoctorDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface DoctorRepository extends JpaRepository<DoctorDbo, Long> {

    boolean existsByNameAndLastName(String name, String lastName);
}