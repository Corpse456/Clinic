package by.gp.clinic.repository;

import by.gp.clinic.dbo.PatientDbo;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Transactional
public interface PatientRepository extends NamedRepository<PatientDbo, Long> {

    Optional<PatientDbo> getByNameAndLastName(String name, String lastName);
}
