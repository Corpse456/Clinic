package by.gp.clinic.repository;

import by.gp.clinic.dbo.UserDbo;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends CustomRepository<UserDbo, Long> {

    @Query("select u.id from UserDbo u where u.alias = ?1")
    Long getIdByAlias(final String name);

    boolean existsByAlias(final String alias);

    boolean existsByDoctorId(final Long id);

    boolean existsByPatientId(final Long id);
}
