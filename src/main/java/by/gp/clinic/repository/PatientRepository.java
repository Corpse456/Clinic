package by.gp.clinic.repository;

import by.gp.clinic.dbo.PatientDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface PatientRepository extends JpaRepository<PatientDbo, Long> {

    boolean existsByNameAndLastName(String name, String lastName);
}