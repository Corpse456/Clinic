package by.gp.clinic.repository;

import by.gp.clinic.dbo.UserDbo;

public interface UserRepository extends CustomRepository<UserDbo, Long> {

    UserDbo getByName(final String name);
}
