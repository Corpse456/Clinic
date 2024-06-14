package by.gp.clinic.repository;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.SpecialityDbo;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface DoctorRepository extends NamedRepository<DoctorDbo, Long> {

    @Query("select s "
        + "from DoctorDbo d "
        + "join fetch SpecialityDbo s on d.speciality.id = s.id "
        + "where d.id = ?1")
    SpecialityDbo getSpecialityById(final Long id);

    Optional<DoctorDbo> getByNameAndLastNameAndSpecialIdentifier(final String name,
        final String lastName,
        final String specialIdentifier);
}
