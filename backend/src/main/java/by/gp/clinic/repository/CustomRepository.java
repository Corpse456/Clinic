package by.gp.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomRepository<Dbo, ID> extends JpaRepository<Dbo, ID>, QuerydslPredicateExecutor<Dbo> {
}
