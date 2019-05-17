package by.gp.clinic.repository;

import by.gp.clinic.dbo.DoctorDbo;
import by.gp.clinic.enumerated.Speciality;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface DoctorRepository extends CustomRepository<DoctorDbo, Long> {

    @Query("select d.speciality from DoctorDbo d where d.id = ?1")
    Speciality getSpecialityById(final Long id);
}