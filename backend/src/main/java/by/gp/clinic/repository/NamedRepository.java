package by.gp.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NamedRepository<Dbo, ID> extends CustomRepository<Dbo, ID> {

    boolean existsByNameAndLastName(String name, String lastName);
}