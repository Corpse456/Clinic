package by.gp.clinic.repository;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.dbo.SpecialityDbo;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface DoctorRepository extends NamedRepository<DoctorDbo, Long> {

    @Query("select d.speciality from DoctorDbo d where d.id = ?1")
    SpecialityDbo getSpecialityById(final Long id);

    Optional<DoctorDbo> getByNameAndLastNameAndSpecialIdentifier(final String name,
                                                                 final String lastName,
                                                                 final String specialIdentifier);
}