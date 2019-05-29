package by.gp.clinic.repository;

import by.gp.clinic.dbo.UserDbo;

public interface UserRepository extends CustomRepository<UserDbo, Long> {

    UserDbo getByAlias(final String name);

    boolean existsByAlias(final String alias);

    boolean existsByDoctorId(final Long id);

    boolean existsByPatientId(final Long id);
}
